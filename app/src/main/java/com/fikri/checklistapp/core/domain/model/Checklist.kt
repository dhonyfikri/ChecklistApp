package com.fikri.checklistapp.core.domain.model

data class Checklist(
    var id: Int? = null,
    var name: String? = null,
    var items: String? = null,
    var checklistCompletionStatus: Boolean? = null
)
