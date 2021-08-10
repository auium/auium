package com.auium.pageobject

import cn.hutool.core.date.DateUtil
import com.auium.driver.driver
import com.auium.driver.findElement
import com.auium.element.Element
import com.auium.element.Selector
import com.auium.element.loading
import mu.KotlinLogging

open class Page(val name: String, val describe: String = name) {

    protected val logger = KotlinLogging.logger {}

    fun find(selector: Selector, maxCount: Int = 5): Element? {
        logger.info { "开始查找：$selector" }
        var count = 1
        var element: Element? = null
        val timer = DateUtil.timer()
        while (element == null && count <= maxCount) {
            loading()
            element = driver.findElement(selector)
            if (element == null) Thread.sleep(999)
            count++
        }
        val success = (element != null)
        if (success) {
            logger.info { "Find element ✅ Find time consuming：${timer.intervalPretty()}" }
        } else {
            logger.info { "Find element ❌ Find time consuming：${timer.intervalPretty()}" }
        }
        return element
    }

}