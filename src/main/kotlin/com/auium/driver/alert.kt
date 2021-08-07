package com.auium.driver

import com.auium.remote.MobileCommand
import com.auium.remote.convert

/**
 * 获取弹窗中的文本信息
 * @return Text
 */
fun Driver.alertText(): String {
    val response = execute(MobileCommand.GET_ALERT_TEXT, wildcards)
    return "${response.value}"
}

/**
 * 弹窗中选择同意按钮
 */
fun Driver.acceptAlert() {
    execute(MobileCommand.ACCEPT_ALERT, wildcards)
}

/**
 * 弹窗中选择拒绝按钮
 */
fun Driver.dismissAlert() {
    execute(MobileCommand.DISMISS_ALERT, wildcards)
}

/**
 * 获取弹窗中的按钮
 */
fun Driver.alertButtons(): List<String> {
    return execute(MobileCommand.GET_ALERT_BUTTON, wildcards).convert() ?: emptyList()
}