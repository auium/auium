package com.auium.element

import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle

interface IElement {

    fun tap()

    fun text(): String

    fun size(): Dimension

    fun rect(): Rectangle

    fun location(): Point

    fun clear()

    fun sendKeys(keys: String)

}