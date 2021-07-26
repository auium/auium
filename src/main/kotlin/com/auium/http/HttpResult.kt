package com.auium.http

data class HttpResult(val code: Int,val headers: Map<String, String>, val body: String) {

}
