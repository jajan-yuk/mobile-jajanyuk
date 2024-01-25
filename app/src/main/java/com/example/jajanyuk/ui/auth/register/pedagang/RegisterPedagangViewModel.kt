package com.example.jajanyuk.ui.auth.register.pedagang

import androidx.lifecycle.ViewModel
import com.example.jajanyuk.data.repository.UserRepository
import okhttp3.RequestBody
import java.io.File

class RegisterPedagangViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun registerPedagang(name: RequestBody, username:  RequestBody, password:  RequestBody, email:  RequestBody, address:  RequestBody, file: File, name_merchant:  RequestBody, phone:  RequestBody)
            = userRepository.registerPedagang(name, username, password, email, address, file, name_merchant, phone)
}