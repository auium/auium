package com.auium.element

import com.auium.driver.driver
import com.auium.driver.findElement
import kotlin.test.Test

class ElementTest {

    @Test
    fun findElementTest() {
        val meElement = driver.findElement(Selector.Name("Me"))
        println(meElement?.text())
    }

    @Test
    fun defaultQueryTest() {
        driver.findElement(Selector.NameContains("Wish"))?.tap()
        loading()
        driver.findElement(Selector.NameContains("Category"))?.tap()
    }

}