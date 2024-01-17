package com.example.jajanyuk.ui.pembeli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityHomePagePembeliBinding
import com.example.jajanyuk.databinding.ActivityHomePedagangBinding

class HomePagePembeliActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePagePembeliBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePagePembeliBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}