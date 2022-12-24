package com.fikri.checklistapp.core.data

import com.fikri.checklistapp.core.data.source.remote.RemoteDataSource
import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistBody
import com.fikri.checklistapp.core.domain.respository_interface.IChecklistRepository

class ChecklistRepository(private val remoteDataSource: RemoteDataSource) : IChecklistRepository {
    override suspend fun createChecklist(token: String, body: CreateChecklistBody) =
        remoteDataSource.createChecklist(token, body)
}