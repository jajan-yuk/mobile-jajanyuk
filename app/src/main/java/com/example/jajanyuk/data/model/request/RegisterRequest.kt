package com.example.jajanyuk.data.model.request

data class RegisterRequest(
    val name: String,
    val username: String,
    val password: String,
    val email: String,
    val alamat: String
)