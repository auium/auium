package com.auium.remote

data class CapabilitiesResult(
    var sessionId: String = "",
    var capabilities: CapabilitiesInfo? = null
) {

}

data class CapabilitiesInfo(
    var device: String = "",
    var browserName: String = "",
    var sdkVersion: String = "",
    var CFBundleIdentifier: String = ""
) {
}