package com.auium.driver

import com.auium.domain.WdaResult
import com.auium.remote.CommandExecutionHelper
import com.auium.remote.MobileCommand
import com.auium.remote.handleResponse
import kotlin.test.Test

class DriverTest {

    private val url = "http://localhost:8200"

    @Test
    fun launchAppTest() {
        val driver = KtDriver(url)
    }

    @Test
    fun requestTest() {
        val response = CommandExecutionHelper(url).execute(MobileCommand.STATUS)
        val status = response.handleResponse<WdaResult>()
        println(status)
    }

}
