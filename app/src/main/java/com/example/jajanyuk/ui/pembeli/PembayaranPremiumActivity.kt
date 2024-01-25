package com.example.jajanyuk.ui.pembeli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityPembayaranPremiumBinding
import com.example.jajanyuk.databinding.ActivityPembayaranTunaiBinding

class PembayaranPremiumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembayaranPremiumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranPremiumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val bayar = Intent(this, PembayaranTunaiActivity::class.java)
            startActivity(bayar)
        }
    }
}