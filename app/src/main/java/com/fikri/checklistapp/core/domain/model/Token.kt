package com.fikri.checklistapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Token(
    var token: String? = null
) : Parcelable
