package com.fikri.checklistapp.core.domain.respository_interface

import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistBody
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.CreateChecklistResponse
import com.fikri.checklistapp.core.data.source.remote.response.DeleteChecklistResponse
import com.fikri.checklistapp.core.domain.model.Checklist

interface IChecklistRepository {
    suspend fun getChecklistList(token: String): Resource<Checklist>

    suspend fun createChecklist(
        token: String,
        body: CreateChecklistBody
    ): ApiResultWrapper<CreateChecklistResponse>

    suspend fun deleteChecklist(
        token: String,
        checklistId: Int
    ): ApiResultWrapper<DeleteChecklistResponse>
}