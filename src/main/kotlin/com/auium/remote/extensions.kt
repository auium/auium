package com.auium.remote

import com.auium.element.Element
import com.auium.json.objectMapper
import com.fasterxml.jackson.core.type.TypeReference

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


fun Response.convertElement(): List<Element> {
    try {
        return objectMapper.convertValue(value, object : TypeReference<List<Element>>() {})
    } catch (ex: Exception) {
    }
    return emptyList()
}