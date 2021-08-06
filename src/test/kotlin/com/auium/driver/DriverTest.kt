package com.auium.driver

import com.auium.remote.CommandExecutionHelper
import com.auium.remote.DriverStatus
import com.auium.remote.MobileCommand
import com.auium.remote.convert
import kotlin.test.Test

class DriverTest {

    private val url = "http://localhost:8100"
    private val bundleId: String = "com.apple.TestFlight"

    @Test
    fun driverBuildTest() {
        val driver = Driver(url)
        driver.locked()
        driver.unlock()
        driver.status()
    }

    @Test
    fun sessionTest() {
        val driver = Driver(url)
        driver.session(bundleId, "zh-tw")
        driver.home()
    }

    @Test
    fun launchAppTest() {
        val driver = Driver(url)
        driver.launch(bundleId, "zh")
    }

    @Test
    fun homeTest() {
        val driver = Driver(url)
        driver.session(bundleId)
        driver.close()
    }

    @Test
    fun requestTest() {
        val response = CommandExecutionHelper(url).execute(MobileCommand.STATUS)
        val status = response.convert<DriverStatus>()
        println(status)
    }

}
