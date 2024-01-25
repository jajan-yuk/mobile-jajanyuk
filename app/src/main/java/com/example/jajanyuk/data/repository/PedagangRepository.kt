package com.example.jajanyuk.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.jajanyuk.R
import com.example.jajanyuk.data.local.datastore.UserPreferences
import com.example.jajanyuk.data.model.request.RegisterRequest
import com.example.jajanyuk.data.model.response.DataUser
import com.example.jajanyuk.data.network.ApiService
import com.example.jajanyuk.utils.ApiError
import com.example.jajanyuk.utils.Result
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException

class PedagangRepository private constructor(
    private val apiService: ApiService,
    private val application: Application
) {

    fun getPedagangNearBy(token: String, longitude: Double, lattitude: Double) = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getPedagangNearBy(token, longitude, lattitude)
            emit(Result.Success(response))
        }catch (e: HttpException) {
            emit(ApiError.handleHttpException(e))
        } catch (exception: IOException) {
            emit(Result.Error(application.resources.getString(R.string.network_error_message)))
        } catch (exception: Exception) {
            emit(Result.Error(exception.message ?: application.resources.getString(R.string.unknown_error)))
        }
    }



    companion object {
        @Volatile
        private var instance: PedagangRepository? = null
        fun getInstance(
            apiService: ApiService,
            application: Application
        ): PedagangRepository =
            instance ?: synchronized(this) {
                instance ?: PedagangRepository(apiService, application)
            }.also { instance = it }
    }
}