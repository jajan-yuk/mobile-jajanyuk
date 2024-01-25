package com.example.jajanyuk.ui.pembeli.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jajanyuk.data.repository.LoginRepository
import com.example.jajanyuk.data.repository.PedagangRepository
import com.example.jajanyuk.di.Injection
import com.example.jajanyuk.ui.auth.LoginViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginViewModel
import com.example.jajanyuk.ui.splash.SplashScreenViewModel

class ProdukViewModelFactory private constructor(private val pedagangRepository: PedagangRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>):  T =
        when {
            modelClass.isAssignableFrom(ProdukViewModel::class.java) ->
                ProdukViewModel(pedagangRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    companion object {
        @Volatile
        private var instance: ProdukViewModelFactory? = null
        fun getInstance(application: Application): ProdukViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ProdukViewModelFactory(Injection.providePedagangRepository(application))
            }.also { instance = it }
    }
}