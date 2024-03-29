package com.example.jajanyuk.ui.auth

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jajanyuk.data.repository.UserRepository
import com.example.jajanyuk.di.Injection
import com.example.jajanyuk.ui.auth.register.pedagang.RegisterPedagangViewModel
import com.example.jajanyuk.ui.auth.register.pembeli.RegisterViewModel
import com.example.jajanyuk.ui.splash.SplashScreenViewModel

class AuthViewModelFactory private constructor(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>):  T =
        when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) ->
                RegisterViewModel(userRepository) as T
            modelClass.isAssignableFrom(RegisterPedagangViewModel::class.java) ->
                RegisterPedagangViewModel(userRepository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    companion object {
        @Volatile
        private var instance: AuthViewModelFactory? = null
        fun getInstance(application: Application): AuthViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: AuthViewModelFactory(Injection.provideUserRepository(application))
            }.also { instance = it }
    }
}