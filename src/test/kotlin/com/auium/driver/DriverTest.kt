package com.auium.driver

import com.auium.element.Selector
import com.auium.remote.CommandExecutionHelper
import com.auium.remote.DriverStatus
import com.auium.remote.MobileCommand
import com.auium.remote.convert
import kotlin.test.Test

class DriverTest {

    private val url = "http://localhost:8100"
    private val bundleId: String = "com.apple.TestFlight"
    private val driver = Driver(url)

    @Test
    fun driverBuildTest() {
        driver.locked()
        driver.unlock()
        driver.status()
    }

    @Test
    fun sessionTest() {
        driver.session(bundleId, "zh-tw")
        driver.home()
    }

    @Test
    fun launchAppTest() {
        driver.launch(bundleId, "zh-hk")
    }

    @Test
    fun terminateTest() {
        driver.terminate(bundleId)
    }

    @Test
    fun homeTest() {
        driver.session(bundleId)
        driver.close()
    }

    @Test
    fun findElementTest() {
        val el = driver.findElement(Selector.ClassChain("**/XCUIElementTypeImage[`name == \"basic_cart-24\"`]"))
        println(el?.rect())
        println(el?.size())
        println(el?.location())
        el?.tap()
    }

    @Test
    fun requestTest() {
        val response = CommandExecutionHelper().execute(MobileCommand.STATUS)
        val status = response.convert<DriverStatus>()
        println(status)
    }

}
