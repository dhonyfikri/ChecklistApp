package com.fikri.checklistapp.core.domain.use_case

import com.fikri.checklistapp.core.domain.respository_interface.IChecklistItemRepository

class ChecklistItemInteractor(private val checklistItemRepository: IChecklistItemRepository) :
    ChecklistItemUseCase {
    override suspend fun getChecklistItemList(
        token: String,
        checklistId: Int
    ) = checklistItemRepository.getChecklistItemList(token, checklistId)
}