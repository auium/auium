package com.auium.element

import com.auium.driver.wildcards
import com.auium.remote.CommandExecutionHelper
import com.auium.remote.MobileCommand
import com.auium.remote.Wildcard
import com.auium.remote.convert
import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.commons.lang3.builder.ToStringBuilder
import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle

class Element : IElement, CommandExecutionHelper() {

    @JsonProperty("ELEMENT")
    var elementId: String? = null
    private var rect: Rectangle? = null

    override fun tap() {
        val location = location()
        execute(MobileCommand.TAP, wildcards, location)
    }

    override fun text(): String {
        wildcards[Wildcard.ELEMENT_ID] = elementId
        return ""
    }

    override fun size(): Dimension {
        val rect = this.rect
        return if (rect == null) rect().size else rect.size
    }

    override fun rect(): Rectangle {
        wildcards[Wildcard.ELEMENT_ID] = elementId
        val rectangle = execute(MobileCommand.GET_ELEMENT_RECT, wildcards).convert<Rectangle>()
        this.rect = rectangle
        return rectangle
    }

    override fun location(): Point {
        val rect = if (this.rect != null) this.rect else rect()
        val x = rect?.x ?: 0
        val y = rect?.y ?: 0
        return Point(x, y)
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