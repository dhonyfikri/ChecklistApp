package com.fikri.checklistapp.core.domain.use_case

import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistItemBody
import com.fikri.checklistapp.core.domain.respository_interface.IChecklistItemRepository

class ChecklistItemInteractor(private val checklistItemRepository: IChecklistItemRepository) :
    ChecklistItemUseCase {
    override suspend fun getChecklistItemList(
        token: String,
        checklistId: Int
    ) = checklistItemRepository.getChecklistItemList(token, checklistId)

    override suspend fun createChecklistItem(
        token: String,
        checklistId: Int,
        body: CreateChecklistItemBody
    ) = checklistItemRepository.createChecklistItem(token, checklistId, body)

    override suspend fun deleteChecklistItem(
        token: String,
        checklistId: Int,
        checklistItemId: Int
    ) = checklistItemRepository.deleteChecklistItem(token, checklistId, checklistItemId)
}