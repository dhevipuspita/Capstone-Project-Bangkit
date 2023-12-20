package com.capstone.cuacatani.network

import com.capstone.cuacatani.response.LoginResponse
import com.capstone.cuacatani.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {
    @POST("register")
    suspend fun signup(
        @Body requestBody: Register
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body requestBody: Login
    ): LoginResponse

}