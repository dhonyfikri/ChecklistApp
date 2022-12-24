package com.fikri.checklistapp.core.domain.respository_interface

import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistBody
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.CreateChecklistResponse

interface IChecklistRepository {
    suspend fun createChecklist(
        token: String,
        body: CreateChecklistBody
    ): ApiResultWrapper<CreateChecklistResponse>
}