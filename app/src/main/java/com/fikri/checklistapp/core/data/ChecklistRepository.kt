package com.fikri.checklistapp.core.data

import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.data.source.remote.RemoteDataSource
import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistBody
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.domain.model.Checklist
import com.fikri.checklistapp.core.domain.respository_interface.IChecklistRepository

class ChecklistRepository(private val remoteDataSource: RemoteDataSource) : IChecklistRepository {
    override suspend fun getChecklistList(token: String): Resource<Checklist> {
        when (val result = remoteDataSource.getChecklistList(token)) {
            is ApiResultWrapper.Success -> {
                val checklistList: ArrayList<Checklist> = arrayListOf()
                result.response.data.map {
                    checklistList.add(
                        Checklist(it.id, it.name, it.items, it.checklistCompletionStatus)
                    )
                }
                return Resource.Success(checklistList)
            }
            is ApiResultWrapper.Error -> {
                val code: Int? = result.code
                val failedType: String? = result.failedType
                val message: String? = result.message
                return Resource.Error(code, failedType, message)
            }
            is ApiResultWrapper.NetworkError -> {
                val failedType: String? = result.failedType
                val message: String? = result.message
                return Resource.NetworkError(failedType, message)
            }
        }
    }

    override suspend fun createChecklist(token: String, body: CreateChecklistBody) =
        remoteDataSource.createChecklist(token, body)
}