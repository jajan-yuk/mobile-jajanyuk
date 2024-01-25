package com.example.jajanyuk.ui.pembeli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityPembayaranBinding
import com.example.jajanyuk.databinding.ActivityPembayaranTunaiBinding

class PembayaranTunaiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembayaranTunaiBinding
    private var total: String? = null
    private var harga: String? = null
    private var nama: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranTunaiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        extractDataFromIntent()

        binding.btnTunai.setOnClickListener {
            val intent = Intent(this, RingkasanPembelianActivity::class.java)
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