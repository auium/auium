package com.auium.json

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

val objectMapper by lazy { buildMapper() }

private fun buildMapper(): ObjectMapper {
    val jacksonObjectMapper = jacksonObjectMapper()
    jacksonObjectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    jacksonObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
        .enable(SerializationFeature.INDENT_OUTPUT)
        .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    return jacksonObjectMapper
}

/**
 * Object to JSON String
 * @return JSON String
 */
fun Any.toJsonString(): String {
    try {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this)
    } catch (ex: Exception) {
        throw RuntimeException(ex.message, ex)
    }
}

/**
 * String to Java Bean
 * @return Object
 */
inline fun <reified T> String?.toObject(): T {
    try {
        return if (this.isNullOrBlank()) {
            T::class.java.getDeclaredConstructor().newInstance()
        } else {
            objectMapper.readValue(this, T::class.java)
        }
    } catch (ex: Exception) {
        throw RuntimeException(ex.message, ex)
    }
}
