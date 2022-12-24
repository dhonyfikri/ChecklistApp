package com.fikri.checklistapp.core.domain.use_case

import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.LoginResponse

interface AuthUseCase {
    suspend fun login(username: String, password: String): ApiResultWrapper<LoginResponse>
}