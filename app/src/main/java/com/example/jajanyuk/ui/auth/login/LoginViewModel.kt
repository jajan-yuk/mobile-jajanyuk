package com.example.jajanyuk.ui.auth.login

import androidx.lifecycle.ViewModel
import com.example.jajanyuk.data.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun login(email: String, password: String) = userRepository.login(email, password)
    fun getUserLogin() = userRepository.getSession()

}