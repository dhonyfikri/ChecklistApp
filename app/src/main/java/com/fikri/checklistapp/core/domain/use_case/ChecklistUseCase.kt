package com.fikri.checklistapp.core.domain.use_case

import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistBody
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.CreateChecklistResponse

interface ChecklistUseCase {
    suspend fun createChecklist(
        token: String,
        body: CreateChecklistBody
    ): ApiResultWrapper<CreateChecklistResponse>
}