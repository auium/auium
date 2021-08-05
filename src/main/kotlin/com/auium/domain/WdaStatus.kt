package com.auium.domain

data class WdaStatus(
    var message: String = "",
    var state: String = "",
    var os: OsInfo = OsInfo(),
    var ready: Boolean = false,
    var WdaBuildInfo: WdaBuildInfo = WdaBuildInfo()
)

data class OsInfo(
    var testmanagerdVersion: String = "",
    var name: String = "",
    var sdkVersion: String = "",
    var version: String = ""
) {
}

data class WdaBuildInfo(
    var time: String = "",
    var productBundleIdentifier: String = ""
) {
}