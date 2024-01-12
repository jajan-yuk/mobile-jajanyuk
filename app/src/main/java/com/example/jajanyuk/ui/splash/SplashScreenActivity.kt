package com.example.jajanyuk.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.example.jajanyuk.MainActivity
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityLoginBinding
import com.example.jajanyuk.databinding.ActivitySplashScreenBinding
import com.example.jajanyuk.ui.auth.LoginViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginActivity
import com.example.jajanyuk.ui.auth.login.LoginViewModel

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        handleUserLogin()
    }

    private fun handleUserLogin() {
        viewModel.getUserLogin().observe(this) { user ->
            val delayIntent = Intent(this, if (user.accessToken.isNullOrEmpty()) LoginActivity::class.java else MainActivity::class.java)

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(delayIntent); finish()
            }, 3000L)
        }
    }
}