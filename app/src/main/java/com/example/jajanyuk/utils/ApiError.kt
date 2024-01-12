package com.example.jajanyuk.utils

import com.example.jajanyuk.data.model.response.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException

object ApiError{
    fun handleHttpException(exception: HttpException): Result.Error {
        val jsonInString = exception.response()?.errorBody()?.string()
        val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
        val errorMessage = errorBody.message
        return Result.Error(errorMessage)
    }
    fun handleHttpExceptionString(exception: HttpException): String {
        val jsonInString = exception.response()?.errorBody()?.string()
        val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
        return errorBody.message ?: "Unknown error"
    }
}