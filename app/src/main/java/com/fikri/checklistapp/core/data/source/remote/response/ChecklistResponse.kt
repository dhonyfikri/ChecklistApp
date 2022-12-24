package com.fikri.checklistapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ChecklistResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("items") var items: String? = null,
    @SerializedName("checklistCompletionStatus") var checklistCompletionStatus: Boolean? = null
)
