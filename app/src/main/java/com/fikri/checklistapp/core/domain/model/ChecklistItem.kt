package com.fikri.checklistapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChecklistItem(
    var id: Int? = null,
    var name: String? = null,
    var itemCompletionStatus: Boolean? = null
) : Parcelable
