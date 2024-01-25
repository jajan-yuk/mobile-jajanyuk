package com.example.jajanyuk.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import com.example.jajanyuk.MainActivity
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityLoginBinding
import com.example.jajanyuk.databinding.ActivitySplashScreenBinding
import com.example.jajanyuk.ui.auth.AuthViewModelFactory
import com.example.jajanyuk.ui.auth.LoginViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginActivity
import com.example.jajanyuk.ui.auth.login.LoginViewModel
import com.example.jajanyuk.ui.pedagang.HomePedagangActivity
import com.example.jajanyuk.ui.pembeli.HomePagePembeliActivity
import com.example.jajanyuk.utils.Result

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }

    private val userViewModel: SplashScreenViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleUserLogin()
    }

    private fun handleUserLogin() {
        viewModel.getUserLogin().observe(this) { user ->

            if (user.accessToken.isNullOrEmpty()) {
                Handler(Looper.getMainLooper()).postDelayed({
                    val loginIntent = Intent(this, LoginActivity::class.java)
                    startActivity(loginIntent)
                    finish()
                }, 3000)
            } else {
               userViewModel.getUser(user.accessToken).observe(this){
                   when(it){
                       is Result.Loading -> return@observe
                       is Result.Success -> {
                            var role = it.data.data?.user?.role?.name.toString()
                           Toast.makeText(this, role, Toast.LENGTH_SHORT).show()
                           if(role == "USER") {
                               Handler(Looper.getMainLooper()).postDelayed({
                                   val homePembeliIntent = Intent(this,   HomePagePembeliActivity::class.java)
                                   startActivity(homePembeliIntent)
                                   finish()
                               }, 3000)

                           } else{

                               Handler(Looper.getMainLooper()).postDelayed({
                                   val homePedagangIntent = Intent(this,  HomePedagangActivity::class.java)
                                   startActivity(homePedagangIntent)
                                   finish()
                               }, 3000)
                           }
                       }
                       is Result.Error -> "Terjadi eror coba lagi"
                   }
               }
            }

        }
    }
}