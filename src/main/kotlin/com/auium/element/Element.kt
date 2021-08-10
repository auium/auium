package com.auium.element

import com.auium.driver.wildcards
import com.auium.remote.CommandExecutionHelper
import com.auium.remote.MobileCommand
import com.auium.remote.Wildcard
import com.auium.remote.convert
import com.fasterxml.jackson.annotation.JsonProperty
import mu.KotlinLogging
import org.apache.commons.lang3.builder.ToStringBuilder
import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle

class Element : IElement, CommandExecutionHelper() {

    @JsonProperty("ELEMENT")
    var elementId: String? = null
    private var rect: Rectangle? = null
    private val logger = KotlinLogging.logger {}

    override fun tap() {
        val center = center()
        wildcards[Wildcard.ELEMENT_ID] = elementId
        logger.debug { "tap: [${center.x}, ${center.y}]" }
        execute(MobileCommand.TAP, wildcards, center)
    }

    override fun repeatTap(count: Int) {
        val center = center()
        wildcards[Wildcard.ELEMENT_ID] = elementId
        logger.debug { "tap: [${center.x}, ${center.y}]" }
        repeat(count) {
            execute(MobileCommand.TAP, wildcards, center)
        }
    }

    override fun text(): String {
        wildcards[Wildcard.ELEMENT_ID] = elementId
        val response = execute(MobileCommand.GET_ELEMENT_TEXT, wildcards)
        return "${response.value}"
    }

    override fun size(): Dimension {
        val rect = this.rect
        wildcards[Wildcard.ELEMENT_ID] = elementId
        return if (rect == null) rect().size else rect.size
    }

    override fun rect(): Rectangle {
        wildcards[Wildcard.ELEMENT_ID] = elementId
        val rectangle = execute(MobileCommand.GET_ELEMENT_RECT, wildcards).convert<Rectangle>()
        this.rect = rectangle
        return rectangle ?: Rectangle()
    }

    override fun location(): Point {
        val rect = if (this.rect != null) this.rect else rect()
        val x = rect?.x ?: 0
        val y = rect?.y ?: 0
        return Point(x, y)
    }

    override fun center(): Point {
        val rect = rect()
        return Point(rect.centerX.toInt(), rect.centerY.toInt())
    }

    override fun clear() {
        wildcards[Wildcard.ELEMENT_ID] = elementId
        execute(MobileCommand.CLEAR, wildcards)
    }

    override fun sendKeys(keys: String) {
        wildcards[Wildcard.ELEMENT_ID] = elementId
        execute(MobileCommand.SEND, wildcards, mapOf("value" to keys.splitToSequence("")))
    }

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }

}