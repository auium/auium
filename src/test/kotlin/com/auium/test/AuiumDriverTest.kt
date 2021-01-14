package com.auium.test

import com.auium.driver.AuiumDriver
import org.testng.annotations.Test
import org.testng.asserts.SoftAssert

class AuiumDriverTest {

    @Test
    fun startIOSTest() {
        val driver = AuiumDriver.buildIOSDriver()
        val assert = SoftAssert()
        assert.assertTrue(driver.pageSource.isNotBlank(), "start app failure")
        assert.assertAll()
    }

}