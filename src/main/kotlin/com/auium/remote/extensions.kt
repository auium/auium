package com.auium.remote

import com.auium.json.objectMapper

/**
 * Any to Java Bean
 * @return T
 */
inline fun <reified T> Response.convert(): T {
    try {
        return objectMapper.convertValue(value, T::class.java)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return T::class.java.getDeclaredConstructor().newInstance()
}
