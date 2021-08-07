package com.auium.remote

import com.auium.remote.http.HttpMethod

object MobileCommand {

    private val commands = mutableMapOf<String, CommandInfo>()
    const val DISMISS_ALERT = "dismissAlert"
    const val ACCEPT_ALERT = "acceptAlert"
    const val GET_ALERT_TEXT = "getAlertText"
    const val NEW_SESSION = "newSession"
    const val STATUS = "status"
    const val GET_PAGE_SOURCE = "getPageSource"
    const val HOME = "home"
    const val TAP = "tap"
    const val LOCKED = "locked"
    const val UNLOCK = "unlock"
    const val LAUNCH = "launchApp"
    const val CLOSE = "closeApp"
    const val TERMINATE = "terminateApp"
    const val FIND_ELEMENT = "findElement"
    const val FIND_ELEMENTS = "findElements"
    const val GET_ELEMENT_RECT = "getElementRect"
    const val INPUT = "input"
    const val SEND = "sendKeys"
    const val CLEAR = "clear"

    init {
        commands[DISMISS_ALERT] = post("/session/:sessionId/alert/dismiss")
        commands[ACCEPT_ALERT] = post("/session/:sessionId/alert/accept")
        commands[GET_ALERT_TEXT] = get("/session/:sessionId/alert/text")
        commands[NEW_SESSION] = post("/session")
        commands[STATUS] = get("/status")
        commands[GET_PAGE_SOURCE] = get("/source")
        commands[HOME] = post("/wda/homescreen")
        commands[LOCKED] = get("/wda/locked")
        commands[UNLOCK] = post("/wda/unlock")
        commands[LAUNCH] = post("/session/:sessionId/wda/apps/launch")
        commands[CLOSE] = delete("/session/:sessionId")
        commands[TERMINATE] = post("/session/:sessionId/wda/apps/terminate")
        commands[FIND_ELEMENT] = post("/session/:sessionId/element")
        commands[FIND_ELEMENTS] = post("/session/:sessionId/elements")
        commands[TAP] = post("/session/:sessionId/wda/tap/0")
        commands[INPUT] = post("/session/:sessionId/wda/keys")
        commands[GET_ELEMENT_RECT] = get("/session/:sessionId/element/:uuid/rect")
        commands[SEND] = post("/session/:sessionId/element/:uuid/value")
        commands[CLEAR] = post("/session/:sessionId/element/:uuid/clear")
    }

    fun get(url: String): CommandInfo {
        return CommandInfo(url, HttpMethod.GET)
    }

    fun post(url: String): CommandInfo {
        return CommandInfo(url, HttpMethod.POST)
    }

    fun delete(url: String): CommandInfo {
        return CommandInfo(url, HttpMethod.DELETE)
    }

    fun getCommand(command: String): CommandInfo? {
        return commands[command]
    }

}