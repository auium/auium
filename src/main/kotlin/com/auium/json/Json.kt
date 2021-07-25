package com.auium.json

import com.fasterxml.jackson.databind.ObjectMapper

val objectMapper by lazy { ObjectMapper() }

/**
 * Object to JSON String
 * @return JSON String
 */
fun Any.jsonString(): String {
    return objectMapper.writeValueAsString(this)
}
