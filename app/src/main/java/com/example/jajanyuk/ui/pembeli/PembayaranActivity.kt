package com.example.jajanyuk.ui.pembeli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jajanyuk.databinding.ActivityDetailPedagangBinding
import com.example.jajanyuk.databinding.ActivityPembayaranBinding

class PembayaranActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembayaranBinding
    private var total: String? = null
    private var harga: String? = null
    private var nama: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)
        extractDataFromIntent()
        binding.tvTitle.setOnClickListener {
            val intent = Intent(this, PembayaranPremiumActivity::class.java)
            intent.putExtra("total", total)
            intent.putExtra("harga", harga)
            intent.putExtra("nama",  nama)
            startActivity(intent)
        }
    }

    private fun extractDataFromIntent() {
        total = intent.getStringExtra("total") ?: ""
        harga = intent.getStringExtra("harga") ?: ""
        nama = intent.getStringExtra("nama") ?: ""
    }



}