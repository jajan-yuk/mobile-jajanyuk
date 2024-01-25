package com.example.jajanyuk.ui.pembeli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityPembayaranBinding
import com.example.jajanyuk.databinding.ActivityPembayaranTunaiBinding

class PembayaranTunaiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembayaranTunaiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranTunaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTunai.setOnClickListener {
            val bayar = Intent(this, PemesananActivity::class.java)
            startActivity(bayar)
        }
    }
}