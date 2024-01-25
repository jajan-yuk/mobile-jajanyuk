package com.example.jajanyuk.ui.pembeli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityPembayaranTunaiBerhasilBinding
import com.example.jajanyuk.databinding.ActivityPembayaranTunaiBinding
import com.example.jajanyuk.ui.pedagang.HomePedagangActivity

class PembayaranTunaiBerhasilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembayaranTunaiBerhasilBinding
    private var total: String? = null
    private var harga: String? = null
    private var nama: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranTunaiBerhasilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        extractDataFromIntent()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,  PembayaranTunaiActivity::class.java)
            intent.putExtra("total", total)
            intent.putExtra("harga", harga)
            intent.putExtra("nama",  nama)
            startActivity(intent)
            finish()
        }, 3000)

    }
    private fun extractDataFromIntent() {
        total = intent.getStringExtra("total") ?: ""
        harga = intent.getStringExtra("harga") ?: ""
        nama = intent.getStringExtra("nama") ?: ""
    }
}