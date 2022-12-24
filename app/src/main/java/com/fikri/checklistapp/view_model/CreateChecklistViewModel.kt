package com.fikri.checklistapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistBody
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.CreateChecklistResponse
import com.fikri.checklistapp.core.domain.model.Token
import com.fikri.checklistapp.core.domain.use_case.ChecklistUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateChecklistViewModel(private val checklistUseCase: ChecklistUseCase) : ViewModel() {
    private val _createChecklistListResponse =
        MutableLiveData<ApiResultWrapper<CreateChecklistResponse>>()
    val createChecklistListResponse: LiveData<ApiResultWrapper<CreateChecklistResponse>> =
        _createChecklistListResponse

    var token: Token? = null

    fun saveNewChecklist(name: String) {
        val createChecklistBody = CreateChecklistBody(name)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result =
                    checklistUseCase.createChecklist(token?.token ?: "", createChecklistBody)
                _createChecklistListResponse.postValue(result)
            }
        }
    }
}