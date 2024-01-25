package com.example.jajanyuk.ui.pembeli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jajanyuk.databinding.ActivityDetailPedagangBinding
import com.example.jajanyuk.databinding.ActivityPembayaranBinding

class PembayaranActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembayaranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.setOnClickListener {
            val bayar = Intent(this, PembayaranPremiumActivity::class.java)
            startActivity(bayar)
        }
    }
}