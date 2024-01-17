package com.example.jajanyuk.ui.auth.register.pedagang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityChooseRegisterBinding
import com.example.jajanyuk.databinding.ActivityRegisterBinding
import com.example.jajanyuk.databinding.ActivityRegisterPedagangBinding

class RegisterPedagangActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterPedagangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPedagangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}