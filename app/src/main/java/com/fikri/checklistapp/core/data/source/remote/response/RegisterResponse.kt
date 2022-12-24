package com.fikri.checklistapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("statusCode") var statusCode: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("errorMessage") var errorMessage: String? = null,
    @SerializedName("data") var data: String? = null
)
