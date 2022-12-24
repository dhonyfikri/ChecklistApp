package com.fikri.checklistapp.core.data.source.remote

import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistBody
import com.fikri.checklistapp.core.data.source.remote.body_params.CreateChecklistItemBody
import com.fikri.checklistapp.core.data.source.remote.body_params.LoginBody
import com.fikri.checklistapp.core.data.source.remote.body_params.RegisterBody
import com.fikri.checklistapp.core.data.source.remote.network.ApiService
import com.fikri.checklistapp.core.data.source.remote.response.*
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
                    ApiResultWrapper.Success(responseBody, "Success Register")
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

    suspend fun getChecklistList(token: String): ApiResultWrapper<ChecklistListResponse> {
        val apiRequest = apiService.getChecklistList("Bearer $token")

        try {
            val response: Response<ChecklistListResponse> = apiRequest.awaitResponse()
            if (response.isSuccessful) {
                val responseBody = response.body()
                return if (responseBody != null) {
                    ApiResultWrapper.Success(responseBody, responseBody.message)
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

    suspend fun createChecklist(
        token: String,
        body: CreateChecklistBody
    ): ApiResultWrapper<CreateChecklistResponse> {
        val apiRequest = apiService.createChecklist("Bearer $token", body)

        try {
            val response: Response<CreateChecklistResponse> = apiRequest.awaitResponse()
            if (response.isSuccessful) {
                val responseBody = response.body()
                return if (responseBody != null) {
                    ApiResultWrapper.Success(responseBody, responseBody.message)
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

    suspend fun deleteChecklist(
        token: String,
        checklistId: Int
    ): ApiResultWrapper<DeleteChecklistResponse> {
        val apiRequest = apiService.deleteChecklist("Bearer $token", checklistId)

        try {
            val response: Response<DeleteChecklistResponse> = apiRequest.awaitResponse()
            if (response.isSuccessful) {
                val responseBody = response.body()
                return if (responseBody != null) {
                    ApiResultWrapper.Success(responseBody, responseBody.message)
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

    suspend fun getChecklistItemList(
        token: String,
        checklistId: Int
    ): ApiResultWrapper<ChecklistItemListResponse> {
        val apiRequest = apiService.getChecklistItemList("Bearer $token", checklistId)

        try {
            val response: Response<ChecklistItemListResponse> = apiRequest.awaitResponse()
            if (response.isSuccessful) {
                val responseBody = response.body()
                return if (responseBody != null) {
                    ApiResultWrapper.Success(responseBody, responseBody.message)
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

    suspend fun createChecklistItem(
        token: String,
        checklistId: Int,
        body: CreateChecklistItemBody
    ): ApiResultWrapper<CreateChecklistItemResponse> {
        val apiRequest = apiService.createChecklistItem("Bearer $token", checklistId, body)

        try {
            val response: Response<CreateChecklistItemResponse> = apiRequest.awaitResponse()
            if (response.isSuccessful) {
                val responseBody = response.body()
                return if (responseBody != null) {
                    ApiResultWrapper.Success(responseBody, responseBody.message)
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