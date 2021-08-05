package com.auium.driver

import com.auium.domain.WdaResult
import com.auium.domain.WdaStatus
import com.auium.exceptions.CreateSessionException
import com.auium.exceptions.WebDriverAgentServerException
import com.auium.http.httpGet
import com.auium.http.httpPost
import com.auium.http.httpPostJson
import com.auium.logger.Logger

class KtDriver(val url: String) {

    var sessionId: String = ""
    var sessionUrl: String = "$url/session/$sessionId"

    init {
        checkServer()
        val locked = locked()
        if (locked) unlock()
    }

    private fun checkServer() {
        try {
            url.httpGet()
        } catch (ex: Exception) {
            throw WebDriverAgentServerException()
        }
    }

    fun locked(): Boolean {
        val (_, _, locked) = "$url/wda/locked".httpGet().obj<Boolean>()
        Logger.debug("是否锁屏：${locked}")
        return locked ?: true
    }

    fun unlock() {
        val (result, _) = "$url/wda/unlock".httpPost().obj<Any>()
        Logger.debug("执行解锁：${if (result) "成功" else "接口请求失败"}")
    }

    fun status(): Boolean {
        val (_, _, result) = "$url/status".httpGet().obj<WdaStatus>()
        Logger.debug("WebDriverAgent状态：$result")
        if (sessionId.isNotBlank()) {
            sessionId
        }
        return result?.ready ?: false
    }

    fun createSession() {
        val json = "{\"capabilities\": {}, \"desiredCapabilities\": {}}"
        val (success, _, result) = "$url/session".httpPostJson(json).obj<WdaResult>()
        if (success) {
            this.sessionId = result?.sessionId ?: throw CreateSessionException()
        }
    }

}