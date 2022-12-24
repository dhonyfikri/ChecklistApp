package com.fikri.checklistapp.core.data.source.remote

import com.fikri.checklistapp.core.data.source.remote.body_params.LoginBody
import com.fikri.checklistapp.core.data.source.remote.body_params.RegisterBody
import com.fikri.checklistapp.core.data.source.remote.network.ApiService
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.data.source.remote.response.LoginResponse
import com.fikri.checklistapp.core.data.source.remote.response.RegisterResponse
import com.fikri.checklistapp.core.ui.modal.ResponseModal
import org.json.JSONObject
import retrofit2.Response
import retrofit2.awaitResponse
import java.io.IOException

@Suppress("BlockingMethodInNonBlockingContext")
class RemoteDataSource(private val apiService: ApiService) {
    suspend fun login(body: LoginBody): ApiResultWrapper<LoginResponse> {
        val apiRequest = apiService.login(body)

        try {
            val response: Response<LoginResponse> = apiRequest.awaitResponse()
            if (response.isSuccessful) {
                val responseBody = response.body()
                return if (responseBody != null) {
                    ApiResultWrapper.Success(responseBody, "Success Login")
                } else {
                    ApiResultWrapper.Error(
                        response.code(),
                        ResponseModal.TYPE_FAILED,
                        "Broken Data"
                    )
                }
            } else {
                var errorMessage: String? = null
                try {
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    errorMessage = jObjError.getString("message")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return ApiResultWrapper.Error(
                    response.code(),
                    ResponseModal.TYPE_MISTAKE,
                    "${response.message()} | $errorMessage"
                )
            }
        } catch (e: IOException) {
            return ApiResultWrapper.NetworkError(
                ResponseModal.TYPE_ERROR,
                "Connection Failed"
            )
        }
    }

    suspend fun register(body: RegisterBody): ApiResultWrapper<RegisterResponse> {
        val apiRequest = apiService.register(body)

        try {
            val response: Response<RegisterResponse> = apiRequest.awaitResponse()
            if (response.isSuccessful) {
                val responseBody = response.body()
                return if (responseBody != null) {
                    ApiResultWrapper.Success(responseBody, "Success Login")
                } else {
                    ApiResultWrapper.Error(
                        response.code(),
                        ResponseModal.TYPE_FAILED,
                        "Broken Data"
                    )
                }
            } else {
                var errorMessage: String? = null
                try {
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    errorMessage = jObjError.getString("message")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return ApiResultWrapper.Error(
                    response.code(),
                    ResponseModal.TYPE_MISTAKE,
                    "${response.message()} | $errorMessage"
                )
            }
        } catch (e: IOException) {
            return ApiResultWrapper.NetworkError(
                ResponseModal.TYPE_ERROR,
                "Connection Failed"
            )
        }
    }
}