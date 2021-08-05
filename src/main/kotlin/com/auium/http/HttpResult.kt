package com.auium.http

import com.auium.domain.WdaError
import com.auium.domain.WdaResult
import com.auium.json.jsonNode
import com.auium.json.jsonString
import com.auium.json.toObject
import com.auium.logger.Logger

data class HttpResult(val code: Int, val headers: Map<String, String>, val body: String) {

    fun success(code: Int = 200): Boolean = (code == this.code && this.body.isNotBlank())

    inline fun <reified T> obj(): Triple<Boolean, WdaResult, T?> {
        try {
            if (!success()) {
                val error = body.jsonNode()?.get("value")?.toObject<WdaError>()
                Logger.error(error)
                return Triple(false, body.jsonString().toObject(), null)
            }
            val info = body.jsonString().toObject<WdaResult>()
            return Triple(true, info, body.jsonNode()?.get("value")?.toObject())
        } catch (ex: Exception) {
            Logger.error(ex.localizedMessage)
        }
        return Triple(false, WdaResult(), null)
    }

}
