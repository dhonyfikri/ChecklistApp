package com.fikri.checklistapp.core.domain.use_case

import com.fikri.checklistapp.core.data.source.remote.body_params.LoginBody
import com.fikri.checklistapp.core.domain.respository_interface.IAuthRepository

class AuthInteractor(private val authRepository: IAuthRepository) : AuthUseCase {
    override suspend fun login(
        body: LoginBody
    ) = authRepository.login(body)
}