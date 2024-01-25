package com.example.jajanyuk.ui.pembeli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.jajanyuk.data.model.response.DataUser
import com.example.jajanyuk.data.model.response.pembeli.DataItemPedagangNerby
import com.example.jajanyuk.data.model.response.pembeli.DetailPedagangResponse
import com.example.jajanyuk.databinding.ActivityDetailPedagangBinding
import com.example.jajanyuk.ui.adapter.DetailProdukAdapter
import com.example.jajanyuk.ui.auth.LoginViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginViewModel
import com.example.jajanyuk.ui.pembeli.viewmodel.ProdukViewModel
import com.example.jajanyuk.ui.pembeli.viewmodel.ProdukViewModelFactory
import com.example.jajanyuk.utils.Result
import com.example.jajanyuk.utils.SnackbarUtils

class DetailPedagangActivity : AppCompatActivity(), DetailProdukAdapter.DetailProdukClickListener {
    private lateinit var binding: ActivityDetailPedagangBinding
    private lateinit var id: String


    // viwemodel
    private val viewModel: ProdukViewModel by viewModels{
        ProdukViewModelFactory.getInstance(application)
    }
    private val  loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }

    // adapter
    private lateinit var dataUser: DataUser
    private val adapter = DetailProdukAdapter(this)
    private var total = 0
    private var harga = 0
    private var nama = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPedagangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPesan.setOnClickListener {
            val intent = Intent(this@DetailPedagangActivity, PembayaranActivity::class.java)
            intent.putExtra("total", total.toString())
            intent.putExtra("harga", harga.toString())
            intent.putExtra("nama", nama.toString())
            startActivity(intent)
        }

    }
    override fun onResume() {
        super.onResume()
        observeUserData()
        val dataProduk = intent.getParcelableExtra<DataItemPedagangNerby>(STORY_INTENT_DATA)
        dataProduk?.let {
            setupProdukDetails(it)
        }
    }
    private fun observeUserData() {
        loginViewModel.getUserLogin().observe(this@DetailPedagangActivity) {
            dataUser = it
        }
    }

    override fun onMinusButtonClick(position: Int) {
        binding.btnPesan.visibility = View.VISIBLE
        val currentItem = adapter.currentList[position]
        harga = currentItem.products?.get(0)?.price!!
        binding.tvTotal.text = (--total).toString()
        if (harga != null) {
            binding.tvHarga.text = (harga * total).toString()
        }

    }

    override fun onPlusButtonClick(position: Int) {
        binding.btnPesan.visibility = View.VISIBLE
        val currentItem = adapter.currentList[position]
        harga = currentItem.products?.get(0)?.price!!
        nama = currentItem.products?.get(0)?.name!!
        binding.tvTotal.text = (++total).toString()
        if (harga != null) {
            binding.tvHarga.text = (harga * total).toString()
        }
    }



    private fun setupProdukDetails(data: DataItemPedagangNerby) {
        with(binding) {
            tvNamaMerchant.text = data.nameMerchant
            tvRating.text = data.summaryProductPedagang?.rating.toString()

            id = data.iD.toString()
            Glide.with(this@DetailPedagangActivity)
                .load(data.image)
                .into(ivGambar)
        }

        setupRecyclerView(adapter)
        observeProduk()
    }
    private fun setupRecyclerView(adapter: DetailProdukAdapter) {
        val layoutManager = LinearLayoutManager(this@DetailPedagangActivity)
        binding.rvProduk.layoutManager = layoutManager
        binding.rvProduk.adapter = adapter
    }
    private fun observeProduk() {
        viewModel.getDetailPedagang(dataUser.accessToken, id).observe(this) { result ->
            handleProdukResult(result, adapter)
        }
    }

    private fun handleProdukResult(result: Result<DetailPedagangResponse>, adapter: DetailProdukAdapter) {
        when (result) {
            is Result.Loading ->  {
                showLoading(true)
            }
            is Result.Success -> {
                showLoading(false)
                val data = listOf(result.data.data)
                adapter.submitList(data)

            }
            is Result.Error -> {
                showLoading(false)
                showSnackBar(result.error)
            }
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    private fun showSnackBar(messageResId: Any) {
        SnackbarUtils.showWithDismissAction(binding.root, messageResId.toString())
    }
    companion object {
        const val STORY_INTENT_DATA = "STORY_INTENT_DATA"
    }

}