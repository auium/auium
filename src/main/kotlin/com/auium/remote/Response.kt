package com.auium.remote

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder

class Response {

    @Volatile
    var value: Any? = null

    @Volatile
    var sessionId: String? = null

    @Volatile
    var id: String? = null

    @Volatile
    var status: Int? = null

    @Volatile
    var state: String? = null

    constructor() {}
    constructor(sessionId: String?) {
        this.sessionId = sessionId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val that = other as Response
        return EqualsBuilder()
            .append(value, that.value)
            .append(sessionId, that.sessionId)
            .append(status, that.status)
            .append(state, that.state)
            .append(id, that.id)
            .isEquals
    }

    override fun hashCode(): Int {
        return HashCodeBuilder(17, 37)
            .append(value)
            .append(sessionId)
            .append(status)
            .append(state)
            .append(id)
            .toHashCode()
    }

    override fun toString(): String {
        return ToStringBuilder(this)
            .append("value", value)
            .append("sessionId", sessionId)
            .append("id", id)
            .append("status", status)
            .append("state", state)
            .toString()
    }
}