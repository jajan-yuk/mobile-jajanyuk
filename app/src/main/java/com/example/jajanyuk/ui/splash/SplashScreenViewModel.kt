package com.example.jajanyuk.ui.splash

import androidx.lifecycle.ViewModel
import com.example.jajanyuk.data.repository.LoginRepository
import com.example.jajanyuk.data.repository.UserRepository

class SplashScreenViewModel (private val loginRepository: LoginRepository) : ViewModel()  {
    fun getUser(token: String) = loginRepository.getUser(token)
}