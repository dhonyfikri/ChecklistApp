package com.fikri.checklistapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("token") var token: String? = null
)
