package com.auium.element

import cn.hutool.core.date.DateUtil
import com.auium.driver.driver
import com.auium.driver.findElement
import com.auium.logger.Logger

fun loading() {
    var count = 1
    val maxCount = 30
    val timer = DateUtil.timer()
    while (findLoading() != null && count <= maxCount) {
       Logger.debug("页面加载中...")
        if (findLoading() == null) {
            Logger.debug("页面加载完成，耗时：${timer.intervalPretty()}...")
            break
        }
        count++
    }
}

private fun findLoading(): Element? {
    try {
        return driver.findElement(Selector.ClassChain("**/XCUIElementTypeActivityIndicator[`visible = 1 AND rect.y > 0`]"))
    } catch (ex: Exception) {
    }
    return null
}