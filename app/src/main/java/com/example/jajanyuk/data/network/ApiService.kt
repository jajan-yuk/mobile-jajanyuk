package com.example.jajanyuk.data.network

import RegisterResponse
import com.example.jajanyuk.data.model.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("register/pedagang")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}