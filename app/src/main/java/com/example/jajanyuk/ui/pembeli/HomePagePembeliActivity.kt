package com.example.jajanyuk.ui.pembeli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.jajanyuk.databinding.ActivityHomePagePembeliBinding
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.example.jajanyuk.utils.SnackbarUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.provider.Settings
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jajanyuk.data.model.response.DataUser
import com.example.jajanyuk.data.model.response.pembeli.PedagangNearByResponse
import com.example.jajanyuk.ui.adapter.ProdukNearByAdapter
import com.example.jajanyuk.ui.auth.LoginViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginViewModel
import com.example.jajanyuk.ui.pembeli.viewmodel.ProdukViewModel
import com.example.jajanyuk.ui.pembeli.viewmodel.ProdukViewModelFactory
import com.example.jajanyuk.utils.Result
class HomePagePembeliActivity : AppCompatActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        when {
            it[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> getLocation()
        }
    }
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding: ActivityHomePagePembeliBinding
    private var location: Location? = null


    // viwemodel
    private val viewModel: ProdukViewModel by viewModels{
        ProdukViewModelFactory.getInstance(application)
    }
    private val  loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }

    // adapter
    private lateinit var dataUser: DataUser
    private val adapter = ProdukNearByAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePagePembeliBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSetting.setOnClickListener {
            val setting = Intent(this, SettingActivity::class.java)
            startActivity(setting)
        }

        binding.btnMap.setOnClickListener {
            if (location != null) {
                val map = Intent(this, MapsPedagangActivity::class.java)
                map.putExtra("lang", location!!.latitude.toString())
                map.putExtra("long", location!!.longitude.toString())
                startActivity(map)
            }else{
                showSnackBar("nyalain lokasi bang")
            }
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        setupRecyclerView(adapter)


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                    performSearch(newText)
                    return true
                }else if(newText == ""){
                    performSearch("")
                    return true
                }
                return false
            }
        })

    }
    private fun performSearch(query: String) {
        viewModel.getSearchPedagang(dataUser.accessToken, query).observe(this){
            handleProdukResult(it, adapter)
        }
    }


    private fun setupRecyclerView(adapter: ProdukNearByAdapter) {
        val layoutManager = LinearLayoutManager(this@HomePagePembeliActivity)
        binding.rvPedagang.layoutManager = layoutManager
        binding.rvPedagang.adapter = adapter
    }
    override fun onResume() {
        super.onResume()
        observeUserData()
        if (checkLocationPermission()) {
            getLocation()
            observeProduk()
        } else {
            requestLocationPermission()
        }
    }

    private fun observeUserData() {
        loginViewModel.getUserLogin().observe(this@HomePagePembeliActivity) {
            dataUser = it
        }
    }
    private fun observeProduk() {
//        val latitudeDouble: Double = location?.latitude ?: 0.0
//        val longitudeDouble: Double = location?.longitude ?: 0.0

        viewModel.getPedagangNearBy(dataUser.accessToken, 0.0, 0.0).observe(this) { result ->
            handleProdukResult(result, adapter)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun handleProdukResult(result: Result<PedagangNearByResponse>, adapter: ProdukNearByAdapter) {
        when (result) {
            is Result.Loading ->  {
                binding.btnMap.visibility = View.GONE
                showLoading(true)
            }
            is Result.Success -> {
                showLoading(false)
                binding.btnMap.visibility = View.VISIBLE
                val data = result.data.data
                if (data.isNullOrEmpty()) {
                    showSnackBar("Produk tidak ada")
                } else {
                    adapter.submitList(data)
                }
            }
            is Result.Error -> {
                showLoading(false)
                binding.btnMap.visibility = View.VISIBLE
                showSnackBar(result.error)
            }
        }
    }


    private fun showEnableLocationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Aktifkan Lokasi")
        builder.setMessage("Untuk menggunakan fitur ini, aktifkan layanan lokasi pada perangkat Anda.")
        builder.setPositiveButton("Pengaturan") { dialog, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
        builder.setNegativeButton("Batal") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
    private fun getLocation() {
        if (
            ContextCompat.checkSelfPermission(
                this@HomePagePembeliActivity, ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                if (it != null) {
                    location = it
                 }
                else {
                    showEnableLocationDialog()
                }
            }
        } else requestPermissionLauncher.launch(arrayOf(ACCESS_COARSE_LOCATION))
    }



    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        requestPermissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
    }

    private fun showSnackBar(messageResId: Any) {
        SnackbarUtils.showWithDismissAction(binding.root, messageResId.toString())
    }
}