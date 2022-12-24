package com.fikri.checklistapp.core.data.source.remote.network

import com.fikri.checklistapp.core.data.source.remote.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") email: String,
        @Field("password") password: String,
    ): Call<LoginResponse>

//    @GET("stories")
//    suspend fun getAllBasicStories(
//        @Header("Authorization") authorization: String,
//        @Query("page") page: Int,
//        @Query("size") size: Int,
//        @Query("location") locationEnable: Int
//    ): AllStoryResponseModel

//    @GET("genre/movie/list")
//    fun getAllGenre(
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String = "en-US"
//    ): Call<GenreListResponse>
//
//    @GET("discover/movie")
//    fun getListMovie(
//        @Query("api_key") apiKey: String,
//        @Query("with_genres") genreId: String? = null,
//        @Query("language") language: String = "en-US",
//        @Query("sort_by") sortBy: String = "popularity.desc",
//        @Query("include_adult") includeAdult: Boolean = false,
//        @Query("include_video") includeVideo: Boolean = false,
//        @Query("page") page: Int = 1,
//        @Query("with_watch_monetization_types") withWatchMonetizationTypes: String = "flatrate",
//    ): Call<MovieListResponse>
//
//    @GET("discover/movie")
//    suspend fun getEndlessListMovieByGenre(
//        @Query("api_key") apiKey: String,
//        @Query("with_genres") genreId: String,
//        @Query("language") language: String = "en-US",
//        @Query("sort_by") sortBy: String = "popularity.desc",
//        @Query("include_adult") includeAdult: Boolean = false,
//        @Query("include_video") includeVideo: Boolean = false,
//        @Query("page") page: Int = 1,
//        @Query("with_watch_monetization_types") withWatchMonetizationTypes: String = "flatrate",
//    ): MovieListResponse
}