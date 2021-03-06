package com.auium.test

import com.auium.driver.AuiumPage
import com.auium.driver.clickOrTap
import io.appium.java_client.MobileBy
import org.testng.annotations.Test

class AuiumDriverTest {

    @Test
    fun `Start App Test`() {
        AuiumPage().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeAny[`name CONTAINS[c] 'Skip'`]")).clickOrTap()
        AuiumPage().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeAny[`name CONTAINS[c] 'Close'`]"),2).clickOrTap()
        AuiumPage().findElement(MobileBy.AccessibilityId("Shopping Bag")).clickOrTap()
    }

}