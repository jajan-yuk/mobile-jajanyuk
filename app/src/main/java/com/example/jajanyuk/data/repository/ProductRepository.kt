package com.example.jajanyuk.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.liveData
import com.example.jajanyuk.R
import com.example.jajanyuk.data.network.ApiService
import com.example.jajanyuk.utils.ApiError
import com.example.jajanyuk.utils.Result
import retrofit2.HttpException
import java.io.IOException

class ProductRepository private constructor(
    private val apiService: ApiService,
    private val application: Application
) {

    fun getProductByUser(user_id: String, id: String) = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getProductByPedagang("Secret_KEY_JAJAN-YUK", user_id)
            emit(Result.Success(response))
        }catch (e: HttpException) {
            Log.e("eror", e.toString())
            emit(ApiError.handleHttpException(e))
        } catch (exception: IOException) {
            emit(Result.Error(application.resources.getString(R.string.network_error_message)))
        } catch (exception: Exception) {
            emit(Result.Error(exception.message ?: application.resources.getString(R.string.unknown_error)))
        }
    }

    companion object {
        @Volatile
        private var instance: ProductRepository? = null
        fun getInstance(
            apiService: ApiService,
            application: Application
        ): ProductRepository =
            instance ?: synchronized(this) {
                instance ?: ProductRepository(apiService, application)
            }.also { instance = it }
    }
}