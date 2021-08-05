package com.auium.http

import okhttp3.Headers
import okhttp3.Request
import okhttp3.internal.EMPTY_REQUEST

val okhttp by lazy { UnsafeOkHttpClient.getUnsafeOkHttpClient() }

/**
 * Execute Http Get request
 * @param headers Request header parameters
 * @return HttpResult
 */
fun String.httpGet(headers: Map<String, String> = mapOf()): HttpResult {
    val request = Request.Builder().url(this).get().headers(buildHeaders(headers)).build()
    return request.execute()
}

/**
 * Execute Http Post request
 * @return HttpResult
 */
fun String.httpPost():HttpResult {
    val request = Request.Builder().url(this).post(EMPTY_REQUEST).build()
    return request.execute()
}

/**
 * Execute Http Post request(Form Body)
 * @param body Request body parameters
 * @param headers Request header parameters
 * @return HttpResult
 */
fun String.httpPostForm(body: Map<String, Any>, headers: Map<String, String> = mapOf()): HttpResult {
    val request = Request.Builder().url(this).formBody(body).headers(buildHeaders(headers)).build()
    return request.execute()
}

/**
 * Execute Http Post request(JSON Body)
 * @param body Request body parameters
 * @param headers Request header parameters
 * @return HttpResult
 */
fun String.httpPostJson(body: Any, headers: Map<String, String> = mapOf()): HttpResult {
    val request = Request.Builder().url(this).jsonBody(body).headers(buildHeaders(headers)).build()
    return request.execute()
}

/**
 * Execute Http Post request(JSON String)
 * @param body Request body parameters
 * @param headers Request header parameters
 * @return HttpResult
 */
fun String.httpPostJson(body: String, headers: Map<String, String> = mapOf()): HttpResult {
    val request = Request.Builder().url(this).jsonBody(body).headers(buildHeaders(headers)).build()
    return request.execute()
}

fun String.httpDelete(headers: Map<String, String> = mapOf()): HttpResult {
    val request = Request.Builder().url(this).delete().headers(buildHeaders(headers)).build()
    return request.execute()
}

/**
 * Execute Http request and return the corresponding result
 * @return HttpResult
 */
private fun Request.execute(): HttpResult {
    val response = okhttp.newCall(this).execute()
    val responseHeaders = mutableMapOf<String, String>()
    for ((name, value) in response.headers) {
        responseHeaders[name] = value
    }
    return HttpResult(response.code, responseHeaders, "${response.body?.string()}")
}

/**
 * Construct Http request header according to Map
 * @param headers Request header parameters
 * @return Http Headers
 */
private fun buildHeaders(headers: Map<String, String>): Headers {
    val headerBuilder = Headers.Builder()
    headers.forEach { (key, value) ->
        headerBuilder.add(key, value)
    }
    return headerBuilder.build()
}