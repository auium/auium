package com.auium.driver

import kotlin.test.Test

class SwipeTest {

    @Test
    fun swipeUpTest() {
        driver.swipeUp()
    }

    @Test
    fun swipeDownTest() {
        driver.swipeDown()
    }

    @Test
    fun swipeLeftTest() {
        driver.swipeLeft()
    }

    @Test
    fun swipeRightTest() {
        driver.swipeRight()
    }

}