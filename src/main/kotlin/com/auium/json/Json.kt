package com.auium.json

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.*
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
        if (this.isNullOrBlank()) {
            return T::class.java.getDeclaredConstructor().newInstance()
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    try {
        return objectMapper.readValue(this, T::class.java)
    } catch (ex: Exception) {
        throw RuntimeException(ex.message, ex)
    }
}

/**
 * String to Java Bean Specify TypeReference
 * @return Object
 */
fun <T> String.toObject(typeReference: TypeReference<T>): T {
    return try {
        objectMapper.readValue(this, typeReference)
    } catch (ex: Exception) {
        throw RuntimeException(ex)
    }
}

/**
 * Get Json Node
 * @return JsonNode
 */
fun String.jsonNode(): JsonNode? {
    return try {
        objectMapper.readTree(this)
    } catch (e: Exception) {
        return null
    }
}

inline fun <reified T> JsonNode.toObject(): T {
    return objectMapper.convertValue(this, T::class.java)
}

/**
 * String to Map<String, Any>
 * @return Map
 */
fun String.toMap(): Map<String, Any?> {
    val typeReference: TypeReference<Map<String, Any?>> = object : TypeReference<Map<String, Any?>>() {}
    return this.toObject(typeReference)
}

/**
 * get Class Type
 * @return JavaType
 */
fun getType(clazz: Class<*>, subClazz: Class<*>): JavaType? {
    return objectMapper.typeFactory.constructCollectionLikeType(clazz, subClazz)
}
