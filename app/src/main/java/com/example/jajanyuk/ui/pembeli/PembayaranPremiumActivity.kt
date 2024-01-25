package com.example.jajanyuk.ui.pembeli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityPembayaranPremiumBinding
import com.example.jajanyuk.databinding.ActivityPembayaranTunaiBinding

class PembayaranPremiumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembayaranPremiumBinding
    private var total: String? = null
    private var harga: String? = null
    private var nama: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranPremiumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        extractDataFromIntent()
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, PembayaranTunaiBerhasilActivity::class.java)
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