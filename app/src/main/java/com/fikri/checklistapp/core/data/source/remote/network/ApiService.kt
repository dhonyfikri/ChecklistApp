package com.fikri.checklistapp.core.data.source.remote.network

import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistBody
import com.fikri.checklistapp.core.data.source.remote.body_params.LoginBody
import com.fikri.checklistapp.core.data.source.remote.body_params.RegisterBody
import com.fikri.checklistapp.core.data.source.remote.response.ChecklistListResponse
import com.fikri.checklistapp.core.data.source.remote.response.CreateChecklistResponse
import com.fikri.checklistapp.core.data.source.remote.response.LoginResponse
import com.fikri.checklistapp.core.data.source.remote.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
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

    @GET("checklist")
    fun getChecklistList(
        @Header("Authorization") token: String
    ): Call<ChecklistListResponse>

    @POST("checklist")
    fun createChecklist(
        @Header("Authorization") token: String,
        @Body body: CreateChecklistBody
    ): Call<CreateChecklistResponse>
}