package com.rayaoption.social.views.data.network.exception

import java.io.IOException

class NotFoundException : IOException() {

    override val message: String?
        get() = "Not Found"
}