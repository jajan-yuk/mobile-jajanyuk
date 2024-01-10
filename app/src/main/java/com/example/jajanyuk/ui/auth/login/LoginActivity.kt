package com.example.jajanyuk.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jajanyuk.databinding.ActivityLoginBinding
import android.text.Editable

import android.text.TextWatcher
import com.example.jajanyuk.ui.auth.register.ChooseRegisterActivity


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvLoginToRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ChooseRegisterActivity::class.java))
        }
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                validatePassword(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun validatePassword(password: String) {
        if (password.length < 8) {
            binding.passordEditTextLayout.error = "Password must be at least 8 characters long"
        } else {
            binding.passordEditTextLayout.error = null
        }
    }

}