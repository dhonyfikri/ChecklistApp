package com.fikri.checklistapp.core.data

import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.data.source.remote.RemoteDataSource
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.domain.model.ChecklistItem
import com.fikri.checklistapp.core.domain.respository_interface.IChecklistItemRepository

class ChecklistItemRepository(private val remoteDataSource: RemoteDataSource) :
    IChecklistItemRepository {
    override suspend fun getChecklistItemList(
        token: String,
        checklistId: Int
    ): Resource<ChecklistItem> {
        when (val result = remoteDataSource.getChecklistItemList(token, checklistId)) {
            is ApiResultWrapper.Success -> {
                val checklistItemList: ArrayList<ChecklistItem> = arrayListOf()
                result.response.data.map {
                    checklistItemList.add(
                        ChecklistItem(it.id, it.name, it.itemCompletionStatus)
                    )
                }
                return Resource.Success(checklistItemList)
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
}