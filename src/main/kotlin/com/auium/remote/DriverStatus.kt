package com.auium.remote

import org.apache.commons.lang3.builder.ToStringBuilder

class DriverStatus {

    var message: String? = ""
    var state: String? = ""
    var ready: Boolean = false
    var build: DriverBuild = DriverBuild()

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }

}

class DriverBuild {
    var time: String = ""
    var productBundleIdentifier: String = ""

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}