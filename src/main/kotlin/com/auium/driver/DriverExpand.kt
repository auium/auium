@file:JvmName("DriverExpand")

package com.auium.driver

import io.appium.java_client.MobileDriver
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.util.concurrent.TimeUnit

private val driver by lazy { AuiumDriver.driver }
private const val IOS_IMAGE = "XCUIElementTypeImage"

fun WebElement?.clickOrTap() {
    if (this != null) {
        if (!isDisplayed || tagName == IOS_IMAGE) tap() else click()
    }
}

private fun WebElement.tap() {
    val x = rect.x.plus(rect.width / 2)
    val y = rect.y.plus(rect.height / 2)
    Touch(driver as MobileDriver<*>).tap(PointOption.point(x, y)).perform()
}

fun WebDriver.implicitlyWait(time: Int, timeUnit: TimeUnit) {
    manage().timeouts().implicitlyWait(time.toLong(), timeUnit)
}