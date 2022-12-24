package com.fikri.checklistapp.core.domain.respository_interface

import com.fikri.checklistapp.core.data.source.remote.body_params.LoginBody
import com.fikri.checklistapp.core.data.source.remote.body_params.RegisterBody
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.LoginResponse
import com.fikri.checklistapp.core.data.source.remote.response.RegisterResponse

interface IAuthRepository {
    suspend fun login(body: LoginBody): ApiResultWrapper<LoginResponse>
    suspend fun register(body: RegisterBody): ApiResultWrapper<RegisterResponse>
}