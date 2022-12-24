package com.fikri.checklistapp.core.domain.use_case

import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistBody
import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistItemBody
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.CreateChecklistItemResponse
import com.fikri.checklistapp.core.data.source.remote.response.CreateChecklistResponse
import com.fikri.checklistapp.core.data.source.remote.response.DeleteChecklistResponse
import com.fikri.checklistapp.core.domain.model.Checklist
import com.fikri.checklistapp.core.domain.model.ChecklistItem

interface ChecklistItemUseCase {
    suspend fun getChecklistItemList(token: String, checklistId: Int): Resource<ChecklistItem>

    suspend fun createChecklistItem(
        token: String,
        checklistId: Int,
        body: CreateChecklistItemBody
    ): ApiResultWrapper<CreateChecklistItemResponse>
}