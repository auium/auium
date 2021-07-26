package com.auium.http

import com.auium.json.jsonString
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

private val jsonMediaType = "application/json; charset=utf-8".toMediaType()

/**
 * Build OkHttp FormBody request
 * @param params Map Params
 * @return Request.Builder
 */
fun Request.Builder.formBody(params: Map<String, Any> = mapOf()): Request.Builder {
    val body = FormBody.Builder()
    params.forEach { (key, value) -> body.add(key, "$value") }
    return post(body.build())
}

/**
 * Build OkHttp JSON request
 * @param json JSON String
 * @return Request.Builder
 */
fun Request.Builder.jsonBody(json: String): Request.Builder {
    val body = json.toRequestBody(jsonMediaType)
    return post(body)
}

/**
 * Build OkHttp JSON request
 * @param obj Object
 * @return Request.Builder
 */
fun Request.Builder.jsonBody(obj: Any): Request.Builder {
    val body = obj.jsonString().toRequestBody(jsonMediaType)
    return post(body)
}
