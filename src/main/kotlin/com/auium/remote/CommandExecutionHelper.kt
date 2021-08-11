package com.auium.remote

import com.auium.http.HttpResult
import com.auium.http.httpDelete
import com.auium.http.httpGet
import com.auium.http.httpPostJson
import com.auium.json.toObject
import com.auium.remote.http.HttpMethod
import mu.KotlinLogging
import java.util.concurrent.locks.ReentrantLock

open class CommandExecutionHelper {

    private val logger = KotlinLogging.logger {}
    private val lock = ReentrantLock()

    fun execute(command: String): Response {
        return execute(command, mutableMapOf())
    }

    fun execute(command: String, wildcards: MutableMap<Wildcard, String>): Response {
        return execute(command, wildcards, mutableMapOf<String, String>())
    }

    fun execute(command: String, wildcards: MutableMap<Wildcard, String>, parameters: Any): Response {
        val commandInfo = MobileCommand.getCommand(command) ?: throw WebDriverAgentException("找不到命令：$command")
        var url = "$webDriverAgentUrl${commandInfo.url}"
        Wildcard.values().forEach { wildcard ->
            val value = wildcards[wildcard]
            if (value != null) {
                url = url.replace(wildcard.key, value)
            }
        }
        logger.debug("Execute command: $command with parameters: $parameters and url: $url")
        var httpResponse: HttpResult? = null
        try {
            lock.lock()
            httpResponse = when (commandInfo.getMethod()) {
                HttpMethod.GET -> {
                    url.httpGet()
                }
                HttpMethod.POST -> {
                    url.httpPostJson(parameters)
                }
                HttpMethod.DELETE -> {
                    url.httpDelete()
                }
            }
        } catch (ex: Exception) {
            logger.error("请求WebDriverAgent异常 ${ex.message}")
        } finally {
            lock.unlock()
        }
        if (httpResponse == null) return buildRetry()
        val response = httpResponse.body.toObject<Response>()
        response.status = httpResponse.code
        return response
    }

    private fun buildRetry(): Response {
        return Response("retry")
    }

}
