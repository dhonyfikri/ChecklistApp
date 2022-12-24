package com.fikri.checklistapp.core.data.source.remote.network

import com.fikri.checklistapp.core.data.source.remote.body_params.LoginBody
import com.fikri.checklistapp.core.data.source.remote.body_params.RegisterBody
import com.fikri.checklistapp.core.data.source.remote.response.LoginResponse
import com.fikri.checklistapp.core.data.source.remote.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(
        @Body body: LoginBody
    ): Call<LoginResponse>

    @POST("register")
    fun register(
        @Body body: RegisterBody
    ): Call<RegisterResponse>
}