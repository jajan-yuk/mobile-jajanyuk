package com.example.jajanyuk.ui.pembeli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.jajanyuk.R
import com.example.jajanyuk.data.model.response.DataUser
import com.example.jajanyuk.databinding.ActivityPembayaranTunaiBerhasilBinding
import com.example.jajanyuk.databinding.ActivityRingkasanPembelianBinding
import com.example.jajanyuk.ui.auth.LoginViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginViewModel
import com.example.jajanyuk.ui.splash.SplashScreenViewModel
import com.example.jajanyuk.utils.Result

class RingkasanPembelianActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }

    private val userViewModel: SplashScreenViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }

    private var total: String? = null
    private var harga: String? = null
    private var nama: String? = null

    private lateinit var dataUser: DataUser
    private lateinit var binding: ActivityRingkasanPembelianBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRingkasanPembelianBinding.inflate(layoutInflater)
        setContentView(binding.root)
        extractDataFromIntent()
        binding.tvTitle.text = nama
        binding.tvHarga.text = ((harga?.toInt() ?: 0) * (total?.toInt() ?: 0)).toString()
        binding.tvTotal.text = total
        binding.tvHarga2.text = ((harga?.toInt() ?: 0) * (total?.toInt() ?: 0)).toString()
        binding.tvHarga3.text = ((harga?.toInt() ?: 0) * (total?.toInt() ?: 0)).toString()
        binding.btnPesan.setOnClickListener {
            val intent = Intent(this, PesananBerhasilActivity::class.java)
            intent.putExtra("total", total)
            intent.putExtra("harga", harga)
            intent.putExtra("nama",  nama)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        observeUserData()
        getDataUser()
    }
    private fun observeUserData() {
        loginViewModel.getUserLogin().observe(this@RingkasanPembelianActivity) {
            dataUser = it
        }
    }
    private fun extractDataFromIntent() {
        total = intent.getStringExtra("total") ?: ""
        harga = intent.getStringExtra("harga") ?: ""
        nama = intent.getStringExtra("nama") ?: ""
    }
    private fun getDataUser(){
        userViewModel.getUser(dataUser.accessToken).observe(this) { result ->
            when(result){
                is Result.Loading ->  return@observe
                is Result.Success -> {
                    val data = result.data.data
                    binding.tvAlamat.text = data?.user?.alamat.toString()

                }
                is Result.Error -> {
                    return@observe
                }
            }
        }
    }


}