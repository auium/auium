package com.auium.driver

import kotlin.test.Test

class DriverTest {

    @Test
    fun unlockTest() {
        val driver = KtDriver("http://localhost:8100")
        driver.locked()
        driver.unlock()
    }

    @Test
    fun launchAppTest() {
        val driver = KtDriver("http://localhost:8100")
        println("是否准备：${driver.status()}")
    }

}
