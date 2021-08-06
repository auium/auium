package com.auium.driver

import com.auium.exceptions.WebDriverAgentException
import com.auium.logger.Logger
import com.auium.remote.*

class Driver(driverUrl: String = "http://localhost:8100") : CommandExecutionHelper(driverUrl) {

    var sessionId: String = ""
    private val wildcards: MutableMap<Wildcard, String> = mutableMapOf()

    init {
        if (locked()) unlock()
        status()
    }

    fun locked(): Boolean {
        val response = execute(MobileCommand.LOCKED)
        getSession(response)
        return response.convert()
    }

    fun unlock() {
        val response = execute(MobileCommand.UNLOCK)
        if (response.status == 500) throw WebDriverAgentException("解锁设备失败!")
        Logger.debug(response)
    }

    fun status() {
        val response = execute(MobileCommand.STATUS)
        getSession(response)
        val status = response.convert<DriverStatus>()
        if (!status.ready) throw WebDriverAgentException("WebDriverAgent状态为[ready]")
    }

    fun session(bundleId: String = "", language: String = "", alertAction: AlertAction? = null) {
        createSession(bundleId, language, alertAction)
    }

    fun launch(bundleId: String, language: String = "") {
        createSession(bundleId, language)
    }

    fun home() {
        execute(MobileCommand.HOME)
    }

    fun close() {
        execute(MobileCommand.CLOSE, wildcards)
    }

    /**
     * 创建会话
     * @param bundleId 应用编号
     * @param language 应用语言
     * @param alertAction 启动时是否处理弹窗
     */
    private fun createSession(bundleId: String = "", language: String = "", alertAction: AlertAction? = null) {
        val empty = mutableMapOf<String, String>()
        val capabilities = mutableMapOf<String, Any>()
        if (bundleId.isNotBlank()) {
            val alwaysMatch = mutableMapOf<String, Any>()
            alwaysMatch["bundleId"] = bundleId
            alwaysMatch["arguments"] = buildLanguage(language)
            alwaysMatch["environment"] = empty
            alwaysMatch["shouldWaitForQuiescence"] = false
            if (alertAction != null) capabilities["defaultAlertAction"] = alertAction.key
            capabilities["alwaysMatch"] = alwaysMatch
        }
        val payload = mutableMapOf(
            "capabilities" to capabilities,
            "desiredCapabilities" to capabilities.getOrDefault("alwaysMatch", empty)
        )
        val response = execute(MobileCommand.NEW_SESSION, mutableMapOf(), payload)
        val capabilitiesResult = response.convert<CapabilitiesResult>()
        if (capabilitiesResult.sessionId.isNotBlank()) {
            this.sessionId = capabilitiesResult.sessionId
            wildcards[Wildcard.SESSION_ID] = capabilitiesResult.sessionId
        } else throw WebDriverAgentException("创建Session失败: $response")
    }

    /**
     * 构建App使用的语言
     * @param language 语言
     * @return 语言参数
     */
    private fun buildLanguage(language: String): MutableList<String> {
        return if (language.isBlank()) mutableListOf() else
            mutableListOf(
                "-AppleLanguages",
                "($language)",
                "-NSLanguages",
                "($language)"
            )
    }

    /**
     * 获取会话信息
     * @param response WebDriverAgent信息
     */
    private fun getSession(response: Response) {
        val sessionId = response.sessionId
        if (sessionId != null && sessionId.isNotBlank()) {
            this.sessionId = sessionId
            wildcards[Wildcard.SESSION_ID] = sessionId
        } else createSession()
    }

}