package com.example.jajanyuk.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jajanyuk.data.repository.LoginRepository
import com.example.jajanyuk.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    fun login(username: String, password: String) = loginRepository.login(username, password)
    fun getUserLogin() = loginRepository.getSession()
    fun deleteUserLogin() = viewModelScope.launch { loginRepository.deleteSession() }

}