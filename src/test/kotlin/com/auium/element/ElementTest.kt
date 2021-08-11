package com.auium.element

import com.auium.driver.driver
import com.auium.driver.findElement
import com.auium.element.Selector.ClassChain
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

    @Test
    fun repeatTapTest() {
        val content = "**/XCUIElementTypeStaticText[`rect.y < 80 AND rect.height IN {10, 11} AND label != null`]"
        driver.findElement(ClassChain(content))?.repeatTap(10)
        driver.findElement(ClassChain("**/XCUIElementTypeTextField"))?.sendKeys("Hong Kon")
        driver.findElement(ClassChain("**/XCUIElementTypeTable[`visible == 1`]/XCUIElementTypeCell[`visible == 1`]"))?.tap()
    }

    @Test
    fun clickElementTest() {
        driver.findElement(Selector.NameContains("Category"))?.click()
    }

}