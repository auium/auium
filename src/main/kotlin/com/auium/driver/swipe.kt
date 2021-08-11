package com.auium.driver

import com.auium.remote.MobileCommand
import kotlin.math.ceil

private val size = driver.windowSize
private val width = size.width
private val height = size.height

/**
 * 向上滑动
 * @param wait 执行滑动时间
 */
fun Driver.swipeUp(wait: Int = 100) {
    swipe(width.div(2), height.div(2), width.div(2), 1, wait)
}

/**
 * 向上滑动
 * @param position 滑动未知
 * @param wait 执行滑动时间
 */
fun Driver.swipeUp(position: Double = 0.2, wait: Int = 100) {
    val startX = ceil(width.times(0.5)).toInt()
    val startY = ceil(height.times(0.8)).toInt()
    val endY = ceil(height.times(position)).toInt()
    swipe(startX, startY, startX, endY, wait)
}

/**
 * 向下滑动
 * @param wait 执行滑动时间
 */
fun Driver.swipeDown(wait: Int = 100) {
    swipe(width.div(2), height.div(2), width.div(2), height.minus(1), wait)
}

/**
 * 向左滑动
 * @param wait 执行滑动时间
 */
fun Driver.swipeLeft(wait: Int = 100) {
    swipe(width, height.div(2), 1, height.div(2), wait)
}

/**
 * 向右滑动
 * @param wait 执行滑动时间
 */
fun Driver.swipeRight(wait: Int = 100) {
    swipe(1, height.div(2), width, height.div(2), wait)
}

/**
 * 滑动操作
 * @param startX 开始位置x
 * @param startY 开始位置y
 * @param endX 结束位置x
 * @param endY 结束位置y
 * @param wait 执行时间（毫秒）
 */
fun Driver.swipe(startX: Int, startY: Int, endX: Int, endY: Int, wait: Int = 100) {
    val params = mutableMapOf(
        "actions" to listOf(
            mapOf("action" to "press", "options" to mapOf("x" to startX, "y" to startY)),
            mapOf("action" to "wait", "options" to mapOf("ms" to wait)),
            mapOf("action" to "moveTo", "options" to mapOf("x" to endX, "y" to endY)),
            mapOf("action" to "release", "options" to mapOf<String, String>())
        )
    )
    execute(MobileCommand.TOUCH_PERFORM, wildcards, params)
}