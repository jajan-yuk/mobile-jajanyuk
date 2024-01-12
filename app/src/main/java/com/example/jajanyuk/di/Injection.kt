package com.example.jajanyuk.di

import android.app.Application
import com.example.jajanyuk.data.local.datastore.UserPreferences
import com.example.jajanyuk.data.local.datastore.dataStore
import com.example.jajanyuk.data.network.ApiConfig
import com.example.jajanyuk.data.repository.UserRepository

object Injection {
    private fun provideApiService() = ApiConfig.getApiService()
    private fun provideUserPreferences(application: Application) =
        UserPreferences.getInstance(application.dataStore)
    fun provideUserRepository(application: Application) =
        UserRepository.getInstance(provideApiService(), application, provideUserPreferences(application))

}