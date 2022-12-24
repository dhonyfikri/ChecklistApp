package com.fikri.checklistapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.DeleteChecklistResponse
import com.fikri.checklistapp.core.domain.model.Checklist
import com.fikri.checklistapp.core.domain.model.Token
import com.fikri.checklistapp.core.domain.use_case.ChecklistItemUseCase
import com.fikri.checklistapp.core.utils.Event

class ChecklistItemListViewModel(private val checklistItemUseCase: ChecklistItemUseCase) :
    ViewModel() {
    private val _checklistList = MutableLiveData<Resource<Checklist>>()
    val checklistList: LiveData<Resource<Checklist>> = _checklistList
    private val _deleteChecklistResponse =
        MutableLiveData<Event<ApiResultWrapper<DeleteChecklistResponse>>>()
    val deleteChecklistResponse: LiveData<Event<ApiResultWrapper<DeleteChecklistResponse>>> =
        _deleteChecklistResponse

    var selectedChecklist: Checklist? = null
    var token: Token? = null

//    init {
//        getChecklistList()
//    }
//
//    fun getChecklistList() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val result = checklistUseCase.getChecklistList(token?.token ?: "")
//                _checklistList.postValue(result)
//            }
//        }
//    }
//
//    fun deleteChecklist(checklistId: Int) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val result = checklistUseCase.deleteChecklist(token?.token ?: "", checklistId)
//                _deleteChecklistResponse.postValue(Event(result))
//            }
//        }
//    }
}