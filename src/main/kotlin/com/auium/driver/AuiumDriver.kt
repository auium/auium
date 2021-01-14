package com.auium.driver

import cn.hutool.core.codec.Base64
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.IOSMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.Platform
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import java.util.concurrent.TimeUnit

object AuiumDriver {

    fun buildIOSDriver(): IOSDriver<WebElement> {
        val capabilities = DesiredCapabilities()
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST)
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS)
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 12")
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.3")
        capabilities.setCapability(MobileCapabilityType.UDID, "EB0FDD8C-04E6-4E82-B863-279AE0870276")
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true)
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false)
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 360)
        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, Base64.decodeStr("enpra28uY29tLlpaS0tP"))
        val driver: IOSDriver<WebElement> = IOSDriver(URL("http://localhost:4723/wd/hub"), capabilities)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
        return driver
    }

}