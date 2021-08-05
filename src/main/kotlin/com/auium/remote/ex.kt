package com.auium.remote

inline fun <reified T> Response.handleResponse(): T {
    val response = this
    if (response.value != null) {
        return response.value as T
    }
    return T::class.java.getDeclaredConstructor().newInstance()
}