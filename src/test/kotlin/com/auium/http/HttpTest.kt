package com.auium.http

import com.auium.driver.DefaultInfo
import com.auium.json.toObject
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
        val result = "http://localhost:8100".httpGet()
        val info: DefaultInfo = result.body.toObject()
        println(info.value?.error)
    }

}