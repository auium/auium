package com.auium.remote

import com.auium.remote.http.HttpMethod

object MobileCommand {

    private val commands = mutableMapOf<String, CommandInfo>()
    const val STATUS = "status"
    const val GET_PAGE_SOURCE = "getPageSource"
    const val HOME = "home"
    const val LOCKED = "locked"
    const val UNLOCK = "unlock"
    const val NEW_SESSION = "newSession"
    const val TOUCH_PERFORM = "touchPerform"
    const val GET_ALERT_TEXT = "getAlertText"
    const val GET_ALERT_BUTTON = "getAlertButtons"
    const val DISMISS_ALERT = "dismissAlert"
    const val ACCEPT_ALERT = "acceptAlert"
    const val TAP = "tap"
    const val LAUNCH = "launchApp"
    const val CLOSE = "closeApp"
    const val TERMINATE = "terminateApp"
    const val FIND_ELEMENT = "findElement"
    const val FIND_ELEMENTS = "findElements"
    const val GET_ELEMENT_TEXT = "getText"
    const val GET_ELEMENT_RECT = "getElementRect"
    const val ELEMENT_SELECT = "selectElement"
    const val INPUT = "input"
    const val SEND = "sendKeys"
    const val CLEAR = "clear"
    const val GET_CURRENT_WINDOW_SIZE = "getCurrentWindowSize"

    init {
        commands[STATUS] = get("/status")
        commands[GET_PAGE_SOURCE] = get("/source")
        commands[HOME] = post("/wda/homescreen")
        commands[LOCKED] = get("/wda/locked")
        commands[UNLOCK] = post("/wda/unlock")
        commands[NEW_SESSION] = post("/session")
        commands[TOUCH_PERFORM] = post("/session/:sessionId/wda/touch/perform")
        commands[ACCEPT_ALERT] = post("/session/:sessionId/alert/accept")
        commands[DISMISS_ALERT] = post("/session/:sessionId/alert/dismiss")
        commands[GET_ALERT_BUTTON] = get("/session/:sessionId/wda/alert/buttons")
        commands[GET_ALERT_TEXT] = get("/session/:sessionId/alert/text")
        commands[LAUNCH] = post("/session/:sessionId/wda/apps/launch")
        commands[CLOSE] = delete("/session/:sessionId")
        commands[TERMINATE] = post("/session/:sessionId/wda/apps/terminate")
        commands[FIND_ELEMENT] = post("/session/:sessionId/element")
        commands[FIND_ELEMENTS] = post("/session/:sessionId/elements")
        commands[TAP] = post("/session/:sessionId/wda/tap/0")
        commands[INPUT] = post("/session/:sessionId/wda/keys")
        commands[GET_ELEMENT_TEXT] = get("/session/:sessionId/element/:uuid/text")
        commands[GET_ELEMENT_RECT] = get("/session/:sessionId/element/:uuid/rect")
        commands[ELEMENT_SELECT] = post("/session/:sessionId/element/:uuid/click")
        commands[SEND] = post("/session/:sessionId/element/:uuid/value")
        commands[CLEAR] = post("/session/:sessionId/element/:uuid/clear")
        commands[GET_CURRENT_WINDOW_SIZE] = get("/session/:sessionId/window/size")
    }

    private fun get(url: String): CommandInfo {
        return CommandInfo(url, HttpMethod.GET)
    }

    private fun post(url: String): CommandInfo {
        return CommandInfo(url, HttpMethod.POST)
    }

    private fun delete(url: String): CommandInfo {
        return CommandInfo(url, HttpMethod.DELETE)
    }

    fun getCommand(command: String): CommandInfo? {
        return commands[command]
    }

}
