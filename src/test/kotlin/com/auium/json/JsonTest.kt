package com.auium.json

import com.auium.domain.WdaError
import kotlin.test.Test

class JsonTest {

    @Test
    fun objectToJsonTest() {
        val info = WdaError()
        println(info.jsonString())
    }

}