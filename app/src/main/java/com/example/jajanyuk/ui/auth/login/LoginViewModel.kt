package com.example.jajanyuk.ui.auth.login

import androidx.lifecycle.ViewModel
import com.example.jajanyuk.data.repository.LoginRepository
import com.example.jajanyuk.data.repository.UserRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    fun login(username: String, password: String) = loginRepository.login(username, password)
    fun getUserLogin() = loginRepository.getSession()

}