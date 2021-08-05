package com.auium.remote

import com.auium.remote.http.HttpMethod
import org.apache.commons.lang3.builder.ToStringBuilder

class CommandInfo(val url: String, method: HttpMethod) {

    private val method: HttpMethod

    fun getMethod(): HttpMethod {
        return method
    }

    init {
        this.method = method
    }

    override fun toString(): String {
        return ToStringBuilder(this)
            .append("url", url)
            .append("method", method)
            .toString()
    }

}
