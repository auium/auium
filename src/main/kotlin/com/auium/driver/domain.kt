package com.auium.driver

data class DefaultInfo(var value: DriverInfo? = DriverInfo(), var sessionId: String? = null)

data class DriverInfo(var error: String = "", var message: String = "", var traceback: String = "")