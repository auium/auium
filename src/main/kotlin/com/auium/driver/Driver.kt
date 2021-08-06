package com.auium.driver

import com.auium.logger.Logger
import com.auium.remote.*
import java.awt.Point

class Driver(driverUrl: String = "http://localhost:8100") : CommandExecutionHelper() {

    var sessionId: String = ""
    val empty = mutableMapOf<String, String>()

    init {
        webDriverAgentUrl = driverUrl
        if (locked()) unlock()
        status()
    }

    /**
     * 是否锁屏
     * @return True Or False
     */
    fun locked(): Boolean {
        val response = execute(MobileCommand.LOCKED)
        getSession(response)
        return response.convert()
    }

    /**
     * 解锁屏幕
     */
    fun unlock() {
        val response = execute(MobileCommand.UNLOCK)
        if (response.status == 500) throw WebDriverAgentException("解锁设备失败!")
        Logger.debug(response)
    }

    /**
     * 查询状态
     */
    fun status() {
        val response = execute(MobileCommand.STATUS)
        getSession(response)
        val status = response.convert<DriverStatus>()
        if (!status.ready) throw WebDriverAgentException("WebDriverAgent状态为[ready]")
    }

    /**
     * 创建会话, 当应用Id为空时会自动创建SessionId, 应用Id不为空时并且应用已经打开则会重新打开
     * @param bundleId 应用Id
     * @param language 应用语言
     * @param alertAction Alert
     */
    fun session(bundleId: String = "", language: String = "", alertAction: AlertAction? = null) {
        createSession(bundleId, language, alertAction)
    }

    /**
     * 启动App, 如果启用启动则不会打开新的
     * @param bundleId 应用ID
     * @param language 应用语言
     */
    fun launch(bundleId: String, language: String = "") {
        val capabilities = mutableMapOf<String, Any>()
        capabilities["bundleId"] = bundleId
        capabilities["arguments"] = buildLanguage(language)
        capabilities["environment"] = empty
        capabilities["shouldWaitForQuiescence"] = false
        execute(MobileCommand.LAUNCH, wildcards, capabilities)
    }

    /**
     * 终止App
     * @param bundleId 应用Id
     */
    fun terminate(bundleId: String) {
        execute(MobileCommand.TERMINATE, wildcards, mutableMapOf("bundleId" to bundleId))
    }

    /**
     * 执行Home按钮
     */
    fun home() {
        execute(MobileCommand.HOME)
    }

    /**
     * 关闭Session
     */
    fun close() {
        execute(MobileCommand.CLOSE, wildcards)
    }

    /**
     * 点击位置
     * @param location 需要点击当位置
     */
    fun tap(location: Point) {
        execute(MobileCommand.TAP, wildcards, location)
    }

    /**
     * 创建会话
     * @param bundleId 应用编号
     * @param language 应用语言
     * @param alertAction 启动时是否处理弹窗
     */
    private fun createSession(bundleId: String = "", language: String = "", alertAction: AlertAction? = null) {
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