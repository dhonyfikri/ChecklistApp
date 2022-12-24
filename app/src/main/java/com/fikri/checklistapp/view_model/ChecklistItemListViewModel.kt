package com.fikri.checklistapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistItemBody
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.CreateChecklistItemResponse
import com.fikri.checklistapp.core.data.source.remote.response.DeleteChecklistItemResponse
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
    private val _createChecklistItemResponse =
        MutableLiveData<ApiResultWrapper<CreateChecklistItemResponse>>()
    val createChecklistItemResponse: LiveData<ApiResultWrapper<CreateChecklistItemResponse>> =
        _createChecklistItemResponse
    private val _showingAddModal = MutableLiveData<Boolean>()
    val showingAddModal: LiveData<Boolean> = _showingAddModal
    private val _deleteChecklistItemResponse =
        MutableLiveData<Event<ApiResultWrapper<DeleteChecklistItemResponse>>>()
    val deleteChecklistItemResponse: LiveData<Event<ApiResultWrapper<DeleteChecklistItemResponse>>> =
        _deleteChecklistItemResponse

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

    fun createChecklistItem(name: String) {
        val createChecklistItemBody = CreateChecklistItemBody(name)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result =
                    checklistItemUseCase.createChecklistItem(
                        token?.token ?: "",
                        selectedChecklist?.id ?: -1,
                        createChecklistItemBody
                    )
                _createChecklistItemResponse.postValue(result)
            }
        }
    }

    fun deleteChecklistItem(checklistItemId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = checklistItemUseCase.deleteChecklistItem(
                    token?.token ?: "",
                    selectedChecklist?.id ?: -1,
                    checklistItemId
                )
                _deleteChecklistItemResponse.postValue(Event(result))
            }
        }
    }

    fun setShowingAddModal(isShowing: Boolean) {
        _showingAddModal.value = isShowing
    }
}