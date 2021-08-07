package com.auium.remote

enum class Wildcard(val key: String) {

    SESSION_ID(":sessionId"),
    ELEMENT_ID(":uuid"),
    REMOTE_BUTTON(":button"),
    NAME(":name");

}