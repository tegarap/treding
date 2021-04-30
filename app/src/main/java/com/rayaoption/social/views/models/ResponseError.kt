package com.rayaoption.social.views.models

import com.google.gson.annotations.SerializedName

data class ResponseError(
    @SerializedName("status_message") val message: String,
    @SerializedName("status_code") val code: Int
)