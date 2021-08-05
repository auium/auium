package com.auium.domain

data class Capabilities(
    var bundleId: String = "",
    var arguments: MutableList<String> = mutableListOf(),
    var environment: MutableMap<String, Any> = mutableMapOf(),
    var shouldWaitForQuiescence: Boolean = false
)

data class CapabilitiesResult(
    var device: String = "",
    var browserName: String = "",
    var sdkVersion: String = "",
    var CFBundleIdentifier: String = ""
) {
}