package com.auium.logger

import cn.hutool.core.date.DateUtil
import cn.hutool.core.lang.Console

object Logger {

    fun debug(log: Any?) {
        Console.log("${DateUtil.now()} >>>>> $log")
    }

    fun error(log: Any?) {
        Console.error("${DateUtil.now()} >>>>> $log")
    }

}