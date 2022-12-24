package com.fikri.checklistapp.core.domain.use_case

import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistBody
import com.fikri.checklistapp.core.domain.respository_interface.IChecklistRepository

class ChecklistInteractor(private val checklistRepository: IChecklistRepository) :
    ChecklistUseCase {
    override suspend fun getChecklistList(token: String) =
        checklistRepository.getChecklistList(token)

    override suspend fun createChecklist(
        token: String,
        body: CreateChecklistBody
    ) = checklistRepository.createChecklist(token, body)
}