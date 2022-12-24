package com.fikri.checklistapp.core.data

import com.fikri.checklistapp.core.data.source.remote.RemoteDataSource
import com.fikri.checklistapp.core.data.source.remote.body_params.LoginBody
import com.fikri.checklistapp.core.domain.respository_interface.IAuthRepository

class AuthRepository(private val remoteDataSource: RemoteDataSource) : IAuthRepository {
    override suspend fun login(
        body: LoginBody
    ) = remoteDataSource.login(body)
}