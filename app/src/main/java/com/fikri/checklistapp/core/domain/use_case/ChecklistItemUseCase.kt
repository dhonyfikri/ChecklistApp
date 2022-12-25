package com.fikri.checklistapp.core.domain.use_case

import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistBody
import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistItemBody
import com.fikri.checklistapp.core.data.source.remote.body_params.UpdateChecklistItemBody
import com.fikri.checklistapp.core.data.source.remote.response.*
import com.fikri.checklistapp.core.domain.model.Checklist
import com.fikri.checklistapp.core.domain.model.ChecklistItem

interface ChecklistItemUseCase {
    suspend fun getChecklistItemList(token: String, checklistId: Int): Resource<ChecklistItem>

    suspend fun createChecklistItem(
        token: String,
        checklistId: Int,
        body: CreateChecklistItemBody
    ): ApiResultWrapper<CreateChecklistItemResponse>

    suspend fun deleteChecklistItem(
        token: String,
        checklistId: Int,
        checklistItemId: Int
    ): ApiResultWrapper<DeleteChecklistItemResponse>

    suspend fun getDetailChecklistItem(
        token: String,
        checklistId: Int,
        checklistItemId: Int
    ): Resource<ChecklistItem>

    suspend fun updateStatusChecklistItem(
        token: String,
        checklistId: Int,
        checklistItemId: Int
    ): ApiResultWrapper<UpdateChecklistItemResponse>

    suspend fun updateNameChecklistItem(
        token: String,
        checklistId: Int,
        checklistItemId: Int,
        body: UpdateChecklistItemBody
    ): ApiResultWrapper<UpdateChecklistItemResponse>
}