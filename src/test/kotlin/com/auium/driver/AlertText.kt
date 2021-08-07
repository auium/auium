package com.auium.driver

import kotlin.test.Test

class AlertText {

    private val driver by lazy { Driver() }

    @Test
    fun getAlertTextTest() {
        println(driver.alertText())
        driver.acceptAlert()
    }

    @Test
    fun dismissAlertTest() {
        println(driver.alertText())
        driver.dismissAlert()
    }

    @Test
    fun getButtonsTest() {
        println(driver.alertButtons())
    }

}