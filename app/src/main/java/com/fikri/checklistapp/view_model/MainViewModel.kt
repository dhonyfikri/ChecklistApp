package com.fikri.checklistapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fikri.checklistapp.core.data.source.remote.body_params.LoginBody
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.LoginResponse
import com.fikri.checklistapp.core.domain.use_case.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _loginData = MutableLiveData<ApiResultWrapper<LoginResponse>>()
    val loginData: LiveData<ApiResultWrapper<LoginResponse>> = _loginData

    fun login(username: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = authUseCase.login(LoginBody(username, password))
                _loginData.postValue(result)
            }
        }
    }
}