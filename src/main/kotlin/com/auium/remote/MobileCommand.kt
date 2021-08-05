package com.auium.remote

import com.auium.remote.http.HttpMethod

object MobileCommand {

    val commands = mutableMapOf<String, CommandInfo>()
    const val DISMISS_ALERT = "dismissAlert"
    const val ACCEPT_ALERT = "acceptAlert"
    const val GET_ALERT_TEXT = "getAlertText"
    const val NEW_SESSION = "newSession"
    const val STATUS = "status"
    const val GET_PAGE_SOURCE = "getPageSource"

    init {
        commands[DISMISS_ALERT] = post("/session/:sessionId/alert/dismiss")
        commands[ACCEPT_ALERT] = post("/session/:sessionId/alert/accept")
        commands[GET_ALERT_TEXT] = get("/session/:sessionId/alert/text")
        commands[NEW_SESSION] = post("/session")
        commands[STATUS] = get("/status")
        commands[GET_PAGE_SOURCE] = post("/source")
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
        return commands.get(command)
    }

}
