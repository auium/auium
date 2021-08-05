package com.auium.http

import com.auium.json.toObject
import java.awt.SystemColor.info
import kotlin.test.Test

class HttpTest {

    @Test
    fun getRequestTest() {
        val url = "https://www.baidu.com"
        val result = url.httpGet()
        println(result.body)
    }

    @Test
    fun jsonFromTest() {
        val url = "https://httpreq.com/shy-boat-zfh3y88w/record"
        val param = mapOf("name" to "zhangsan", "age" to 18)
        val result = url.httpPostForm(param)
        println(result)
    }

    @Test
    fun jsonPostTest() {
        val url = "https://httpreq.com/shy-boat-zfh3y88w/record"
        val param = mapOf("name" to "zhangsan", "age" to 18)
        val result = url.httpPostJson(param)
        println(result)
    }

    @Test
    fun jsonToBeanTest() {
    }

}