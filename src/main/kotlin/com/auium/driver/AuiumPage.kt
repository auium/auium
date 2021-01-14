package com.auium.driver

import cn.hutool.core.date.DateUtil
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

class AuiumPage {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        private val driver by lazy { AuiumDriver.driver }
    }

    fun findElement(by: By, findCount: Int = 10): WebElement? {
        var count = 1
        var element: WebElement? = null
        driver.implicitlyWait(1, TimeUnit.SECONDS)
        val timer = DateUtil.timer()
        while (element == null && count <= findCount) {
            element = driver.find(by)
            count++
        }
        println("查找耗时：${timer.intervalPretty()}")
        return element
    }

    private fun WebDriver.find(by: By): WebElement? {
        return try {
            findElement(by)
        } catch (ex: NoSuchElementException) {
            null
        }
    }

}