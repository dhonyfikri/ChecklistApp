package com.fikri.checklistapp.core.domain.respository_interface

import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.LoginResponse

interface IAuthRepository {
    suspend fun login(username: String, password: String): ApiResultWrapper<LoginResponse>
}