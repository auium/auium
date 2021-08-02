package com.auium.json

import com.auium.driver.DefaultInfo
import kotlin.test.Test

class JsonTest {

    @Test
    fun objectToJsonTest() {
        val info = DefaultInfo()
        println(info.jsonString())
    }

}