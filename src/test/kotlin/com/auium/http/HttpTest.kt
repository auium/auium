package com.auium.http

import kotlin.test.Test

class HttpTest {

    @Test
    fun getRequestTest() {
        val url = "https://www.baidu.com"
        val result = url.get()
        println(result)
    }

}