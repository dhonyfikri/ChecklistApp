package com.fikri.checklistapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistItemBody
import com.fikri.checklistapp.core.data.source.remote.body_params.UpdateChecklistItemBody
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.CreateChecklistItemResponse
import com.fikri.checklistapp.core.data.source.remote.response.DeleteChecklistItemResponse
import com.fikri.checklistapp.core.data.source.remote.response.UpdateChecklistItemResponse
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
    private val _updateChecklistItemResponse =
        MutableLiveData<Event<ApiResultWrapper<UpdateChecklistItemResponse>>>()
    val updateChecklistItemResponse: LiveData<Event<ApiResultWrapper<UpdateChecklistItemResponse>>> =
        _updateChecklistItemResponse
    private val _updateStatusChecklistItemResponse =
        MutableLiveData<Event<ApiResultWrapper<UpdateChecklistItemResponse>>>()
    val updateStatusChecklistItemResponse: LiveData<Event<ApiResultWrapper<UpdateChecklistItemResponse>>> =
        _updateStatusChecklistItemResponse
    private val _showingDetailModal = MutableLiveData<Boolean>()
    val showingDetailModal: LiveData<Boolean> = _showingDetailModal
    private val _showingUpdateModal = MutableLiveData<Boolean>()
    val showingUpdateModal: LiveData<Boolean> = _showingUpdateModal
    private val _updatedActivity = MutableLiveData(false)
    val updatedActivity: LiveData<Boolean> = _updatedActivity

    var detailChecklistItem: ChecklistItem? = null
    var selectedChecklist: Checklist? = null
    var selectedChecklistItem: ChecklistItem? = null
    var token: Token? = null
    var currentAddChecklistName = ""
    var currentUpdateChecklistName: String? = null

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

    fun getDetailChecklistItem(checklistItemId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = checklistItemUseCase.getDetailChecklistItem(
                    token?.token ?: "",
                    selectedChecklist?.id ?: -1,
                    checklistItemId
                )
                when (result) {
                    is Resource.Success -> {
                        detailChecklistItem = result.data[0]
                        _showingDetailModal.postValue(true)
                    }
                    else -> {
                        // do something
                    }
                }
            }
        }
    }

    fun updateChecklistItem(checklistItemId: Int, newName: String) {
        val updateChecklistItemBody = UpdateChecklistItemBody(newName)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = checklistItemUseCase.updateNameChecklistItem(
                    token?.token ?: "",
                    selectedChecklist?.id ?: -1,
                    checklistItemId,
                    updateChecklistItemBody
                )
                _updateChecklistItemResponse.postValue(Event(result))
            }
        }
    }

    fun updateStatusChecklistItem(checklistItemId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = checklistItemUseCase.updateStatusChecklistItem(
                    token?.token ?: "",
                    selectedChecklist?.id ?: -1,
                    checklistItemId
                )
                _updateStatusChecklistItemResponse.postValue(Event(result))
            }
        }
    }

    fun setUpdatedActivity() {
        _updatedActivity.value = true
    }

    fun setShowingAddModal(isShowing: Boolean) {
        _showingAddModal.value = isShowing
    }

    fun dismissDetailModal() {
        _showingDetailModal.value = false
    }

    fun setShowingUpdateModal(isShowing: Boolean) {
        _showingUpdateModal.value = isShowing
    }
}