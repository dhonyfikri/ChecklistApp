package com.fikri.checklistapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.domain.model.Checklist
import com.fikri.checklistapp.core.domain.model.ChecklistItem
import com.fikri.checklistapp.core.domain.model.Token
import com.fikri.checklistapp.core.domain.use_case.ChecklistItemUseCase
import com.fikri.checklistapp.core.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChecklistItemListViewModel(private val checklistItemUseCase: ChecklistItemUseCase) :
    ViewModel() {
    val initialGetChecklistItemList: LiveData<Event<Boolean>> =
        MutableLiveData(Event(true))
    private val _checklistItemList = MutableLiveData<Resource<ChecklistItem>>()
    val checklistItemList: LiveData<Resource<ChecklistItem>> = _checklistItemList
//    private val _deleteChecklistResponse =
//        MutableLiveData<Event<ApiResultWrapper<DeleteChecklistResponse>>>()
//    val deleteChecklistResponse: LiveData<Event<ApiResultWrapper<DeleteChecklistResponse>>> =
//        _deleteChecklistResponse

    var selectedChecklist: Checklist? = null
    var token: Token? = null

    fun getChecklistItemList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = checklistItemUseCase.getChecklistItemList(
                    token?.token ?: "",
                    selectedChecklist?.id ?: -1
                )
                _checklistItemList.postValue(result)
            }
        }
    }

//    fun deleteChecklist(checklistId: Int) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val result = checklistUseCase.deleteChecklist(token?.token ?: "", checklistId)
//                _deleteChecklistResponse.postValue(Event(result))
//            }
//        }
//    }
}