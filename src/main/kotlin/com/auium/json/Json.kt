package com.auium.json

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.*

val objectMapper by lazy { buildMapper() }

private fun buildMapper(): ObjectMapper {
    return ObjectMapper()
        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
        .enable(SerializationFeature.INDENT_OUTPUT)
        .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
}

/**
 * Object to JSON String
 * @return JSON String
 */
fun Any.jsonString(): String {
    return objectMapper.writeValueAsString(this)
}

/**
 * String to Java Bean
 * @return Object
 */
inline fun <reified T> String.toObject(): T {
    return objectMapper.readValue(this, T::class.java)
}

/**
 * Get Json Node
 */
fun String.jsonNode(): JsonNode? {
    return try {
        objectMapper.readTree(this)
    } catch (e: Exception) {
        return null
    }
}

fun String.toMap(): Map<String, Any?> {
    val typeReference: TypeReference<Map<String, Any?>> = object : TypeReference<Map<String, Any?>>() {}
    return toObject(this, typeReference)
}

fun <T> toObject(str: String, typeReference: TypeReference<T>): T {
    return try {
        objectMapper.readValue(str, typeReference)
    } catch (ex: Exception) {
        throw RuntimeException(ex)
    }
}

fun getType(clazz: Class<*>, subClazz: Class<*>): JavaType? {
    return objectMapper.typeFactory.constructCollectionLikeType(clazz, subClazz)
}
