package com.fikri.checklistapp.core.data.source.remote.network

import com.fikri.checklistapp.core.data.source.remote.body_params.*
import com.fikri.checklistapp.core.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.*

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

    @DELETE("checklist/{checklist_id}")
    fun deleteChecklist(
        @Header("Authorization") token: String,
        @Path("checklist_id") checklistId: Int
    ): Call<DeleteChecklistResponse>

    @GET("checklist/{checklist_id}/item")
    fun getChecklistItemList(
        @Header("Authorization") token: String,
        @Path("checklist_id") checklistId: Int
    ): Call<ChecklistItemListResponse>

    @POST("checklist/{checklist_id}/item")
    fun createChecklistItem(
        @Header("Authorization") token: String,
        @Path("checklist_id") checklistId: Int,
        @Body body: CreateChecklistItemBody
    ): Call<CreateChecklistItemResponse>

    @DELETE("checklist/{checklist_id}/item/{checklist_item_id}")
    fun deleteChecklistItem(
        @Header("Authorization") token: String,
        @Path("checklist_id") checklistId: Int,
        @Path("checklist_item_id") checklistItemId: Int
    ): Call<DeleteChecklistItemResponse>

    @GET("checklist/{checklist_id}/item/{checklist_item_id}")
    fun getDetailChecklistItem(
        @Header("Authorization") token: String,
        @Path("checklist_id") checklistId: Int,
        @Path("checklist_item_id") checklistItemId: Int
    ): Call<DetailChecklistItemResponse>

    @PUT("checklist/{checklist_id}/item/{checklist_item_id}")
    fun updateStatusChecklistItem(
        @Header("Authorization") token: String,
        @Path("checklist_id") checklistId: Int,
        @Path("checklist_item_id") checklistItemId: Int
    ): Call<UpdateChecklistItemResponse>

    @PUT("checklist/{checklist_id}/item/rename/{checklist_item_id}")
    fun updateNameChecklistItem(
        @Header("Authorization") token: String,
        @Path("checklist_id") checklistId: Int,
        @Path("checklist_item_id") checklistItemId: Int,
        @Body body: UpdateChecklistItemBody
    ): Call<UpdateChecklistItemResponse>
}