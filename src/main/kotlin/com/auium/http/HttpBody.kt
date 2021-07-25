package com.auium.http

import com.auium.json.jsonString
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

private val jsonMediaType = "application/json; charset=utf-8".toMediaType()

fun Request.Builder.formBody(params: Map<String, Any> = mapOf()): Request.Builder {
    val body = FormBody.Builder()
    params.forEach { (key, value) -> body.add(key, "$value") }
    return post(body.build())
}

fun Request.Builder.jsonBody(json: String): Request.Builder {
    val body = json.toRequestBody(jsonMediaType)
    return post(body)
}

fun Request.Builder.jsonBody(obj: Any): Request.Builder {
    val body = obj.jsonString().toRequestBody(jsonMediaType)
    return post(body)
}