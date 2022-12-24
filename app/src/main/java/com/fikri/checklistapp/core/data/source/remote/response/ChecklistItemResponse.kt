package com.fikri.checklistapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ChecklistItemResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("itemCompletionStatus") var itemCompletionStatus: Boolean? = null
)
