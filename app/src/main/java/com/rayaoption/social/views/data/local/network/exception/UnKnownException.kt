package com.rayaoption.social.views.data.network.exception

import java.lang.Exception

class UnKnownException : Exception() {

    override val message: String?
        get() = "Some Unknown Error Occurred"
}