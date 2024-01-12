package com.example.jajanyuk.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.jajanyuk.R
import com.example.jajanyuk.data.local.datastore.UserPreferences
import com.example.jajanyuk.data.model.request.LoginRequest
import com.example.jajanyuk.data.model.request.RegisterRequest
import com.example.jajanyuk.data.model.response.DataUser
import com.example.jajanyuk.data.network.ApiService
import com.example.jajanyuk.utils.ApiError
import retrofit2.HttpException
import java.io.IOException
import com.example.jajanyuk.utils.Result

class UserRepository private constructor(
    private val apiService: ApiService,
    private val application: Application,
    private val userPref: UserPreferences
) {
    private suspend fun <T> apiCall(call: suspend () -> T): Result<T> = try {
        Result.Success(call())
    } catch (e: HttpException) {
        Result.Error(ApiError.handleHttpExceptionString(e))
    } catch (exception: IOException) {
        Result.Error(application.resources.getString(R.string.network_error_message))
    } catch (exception: Exception) {
        Result.Error(exception.message ?: application.resources.getString(R.string.unknown_error))
    }
    fun register(name: String, username: String, password: String, email: String, alamat: String) = liveData {
        emit(Result.Loading)
        emit(apiCall { apiService.register(RegisterRequest(name, username, password, email, alamat)) })
    }

    fun login(username: String, password: String) = liveData {
        emit(Result.Loading)
        emit(apiCall {
            val response = apiService.login(LoginRequest(username, password))
            saveSession(response.data)
            response
        })
    }

    suspend fun saveSession(data: DataUser) = userPref.saveSession(data)
    fun getSession(): LiveData<DataUser> = userPref.getSession().asLiveData()
    suspend fun deleteSession() = userPref.deleteSession()
    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            application: Application,
            pref: UserPreferences
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, application, pref)
            }.also { instance = it }
    }
}