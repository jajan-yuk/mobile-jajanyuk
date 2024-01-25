package com.example.jajanyuk.ui.pembeli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.jajanyuk.R
import com.example.jajanyuk.data.model.response.DataUser
import com.example.jajanyuk.databinding.ActivityPembayaranTunaiBerhasilBinding
import com.example.jajanyuk.databinding.ActivityRingkasanPembelianBinding
import com.example.jajanyuk.ui.auth.LoginViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginViewModel
import com.example.jajanyuk.ui.splash.SplashScreenViewModel

class RingkasanPembelianActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }

    private val userViewModel: SplashScreenViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }
    private lateinit var dataUser: DataUser
    private lateinit var binding: ActivityRingkasanPembelianBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRingkasanPembelianBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}