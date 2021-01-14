package com.auium.driver

import io.appium.java_client.PerformsTouchActions
import io.appium.java_client.TouchAction

/**
 * 解决Kotlin无法使用TouchAction问题
 * Kotlin无法直接识别TouchAction
 */
class Touch(performsActions: PerformsTouchActions) : TouchAction<Touch>(performsActions)