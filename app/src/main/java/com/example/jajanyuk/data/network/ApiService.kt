package com.example.jajanyuk.data.network

import RegisterResponse
import com.example.jajanyuk.data.model.request.LoginRequest
import com.example.jajanyuk.data.model.request.RegisterRequest
import com.example.jajanyuk.data.model.response.LoginResponse
import com.example.jajanyuk.data.model.response.UserResponse
import com.example.jajanyuk.data.model.response.auth.RegisterPedagangResponse
import com.example.jajanyuk.data.model.response.pembeli.PedagangNearByResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("register/user")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @Multipart
    @POST("register/pedagang")
    suspend fun registerPedagang(
        @Part("name") name: RequestBody,
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
        @Part("email") email: RequestBody,
        @Part("address") address: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("name_merchant") name_merchant: RequestBody,
        @Part("phone") phone: RequestBody,
    ): RegisterPedagangResponse

    @GET("current-user")
    suspend fun getUser(
        @Header("Authorization") token: String
    ): UserResponse

    @GET("pedagang")
    suspend fun getPedagangNearBy(
        @Header("Authorization") token: String,
        @Query("longitude") longitude: Double = 106.7904283,
        @Query("latitude") latitude: Double = -6.7670411,
    ): PedagangNearByResponse

}