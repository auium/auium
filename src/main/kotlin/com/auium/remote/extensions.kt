package com.auium.remote

import com.auium.json.objectMapper

var webDriverAgentUrl: String = ""

/**
 * Any to Java Bean
 * @return T
 */
inline fun <reified T> Response.convert(): T? {
    try {
        return objectMapper.convertValue(value, T::class.java)
    } catch (ex: Exception) {
    }
    return null
}
