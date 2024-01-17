package com.example.jajanyuk.ui.auth.register.pedagang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityRegisterMerchantAcitivtyBinding
import com.example.jajanyuk.databinding.ActivityRegisterPedagangBinding
import com.example.jajanyuk.ui.auth.register.pembeli.RegisterActivity

class RegisterMerchantAcitivty : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterMerchantAcitivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterMerchantAcitivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.btnNext.setOnClickListener {
                startActivity(Intent(this@RegisterMerchantAcitivty, RegisterPedagangActivity::class.java))
                finish()
        }

    }
}