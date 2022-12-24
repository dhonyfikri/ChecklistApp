package com.fikri.checklistapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fikri.checklistapp.core.data.source.remote.body_params.RegisterBody
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.RegisterResponse
import com.fikri.checklistapp.core.domain.use_case.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _registerData = MutableLiveData<ApiResultWrapper<RegisterResponse>>()
    val registerData: LiveData<ApiResultWrapper<RegisterResponse>> = _registerData

    fun register(email: String, username: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = authUseCase.register(RegisterBody(email, username, password))
                _registerData.postValue(result)
            }
        }
    }
}