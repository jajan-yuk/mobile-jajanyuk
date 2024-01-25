package com.example.jajanyuk.ui.auth

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jajanyuk.data.repository.LoginRepository
import com.example.jajanyuk.di.Injection
import com.example.jajanyuk.ui.auth.login.LoginViewModel
import com.example.jajanyuk.ui.splash.SplashScreenViewModel

class LoginViewModelFactory private constructor(private val loginRepository: LoginRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>):  T =
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(loginRepository) as T
            modelClass.isAssignableFrom(SplashScreenViewModel::class.java) ->
                SplashScreenViewModel(loginRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    companion object {
        @Volatile
        private var instance: LoginViewModelFactory? = null
        fun getInstance(application: Application): LoginViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: LoginViewModelFactory(Injection.provideLoginUserRepository(application))
            }.also { instance = it }
    }
}