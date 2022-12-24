package com.fikri.checklistapp.core.domain.respository_interface

import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistItemBody
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.CreateChecklistItemResponse
import com.fikri.checklistapp.core.data.source.remote.response.DeleteChecklistItemResponse
import com.fikri.checklistapp.core.domain.model.ChecklistItem

interface IChecklistItemRepository {
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
}