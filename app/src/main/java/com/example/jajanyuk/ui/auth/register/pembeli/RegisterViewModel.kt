package com.example.jajanyuk.ui.auth.register.pembeli

import androidx.lifecycle.ViewModel
import com.example.jajanyuk.data.repository.UserRepository

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun register(name: String, username: String, password: String, email: String, alamat: String) =
        userRepository.register(name, username, password, email, alamat)
}