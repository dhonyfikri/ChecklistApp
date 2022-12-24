package com.fikri.checklistapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.domain.model.Checklist
import com.fikri.checklistapp.core.domain.model.Token
import com.fikri.checklistapp.core.domain.use_case.ChecklistUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val checklistUseCase: ChecklistUseCase) : ViewModel() {
    private val _checklistList = MutableLiveData<Resource<Checklist>>()
    val checklistList: LiveData<Resource<Checklist>> = _checklistList

    var token: Token? = null

    init {
        getChecklistList()
    }

    fun getChecklistList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = checklistUseCase.getChecklistList(token?.token ?: "")
                _checklistList.postValue(result)
            }
        }
    }
}