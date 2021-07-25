package com.auium.http

import okhttp3.Headers
import okhttp3.Request

val okhttp by lazy { UnsafeOkHttpClient.getUnsafeOkHttpClient() }

fun String.get(headers: Map<String, String> = mapOf()) {
    val request = Request.Builder().url(this).headers(buildHeaders(headers)).build()
    val response = okhttp.newCall(request).execute()
    println(response.body?.string())
}

private fun buildHeaders(headers: Map<String, String>): Headers {
    val headerBuilder = Headers.Builder()
    headers.forEach { (key, value) ->
        headerBuilder.add(key, value)
    }
    return headerBuilder.build()
}