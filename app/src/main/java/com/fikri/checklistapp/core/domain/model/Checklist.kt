package com.fikri.checklistapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Checklist(
    var id: Int? = null,
    var name: String? = null,
    var items: ArrayList<ChecklistItem>? = null,
    var checklistCompletionStatus: Boolean? = null
) : Parcelable
