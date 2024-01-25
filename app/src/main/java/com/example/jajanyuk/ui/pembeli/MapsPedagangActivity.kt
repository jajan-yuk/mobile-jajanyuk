package com.example.jajanyuk.ui.pembeli

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jajanyuk.R
import com.example.jajanyuk.data.model.response.DataUser
import com.example.jajanyuk.data.model.response.pembeli.DataItemPedagangNerby
import com.example.jajanyuk.data.model.response.pembeli.PedagangNearByResponse

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.jajanyuk.databinding.ActivityMapsPedagangBinding
import com.example.jajanyuk.ui.adapter.ProdukMapAdapter
import com.example.jajanyuk.ui.adapter.ProdukNearByAdapter
import com.example.jajanyuk.ui.auth.LoginViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginViewModel
import com.example.jajanyuk.ui.pembeli.viewmodel.ProdukViewModel
import com.example.jajanyuk.ui.pembeli.viewmodel.ProdukViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.example.jajanyuk.utils.Result
import com.example.jajanyuk.utils.SnackbarUtils

class MapsPedagangActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsPedagangBinding
    private val boundsBuilder = LatLngBounds.Builder()
    private val viewModel: ProdukViewModel by viewModels {
        ProdukViewModelFactory.getInstance(application)
    }
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { if (it) getLocationForDevice() }
    private lateinit var dataUser: DataUser
    private val  loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }

    private val adapter = ProdukMapAdapter()

    private var lang: String? = null
    private var long: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMapsPedagangBinding.inflate(layoutInflater)
         setContentView(binding.root)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)
         getDataUserLogin()

        setupRecyclerView(adapter)
    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMap.isMyLocationEnabled = true

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true
        val intent = intent
        lang = intent.getStringExtra("lang") ?: "defaultLang"
        long = intent.getStringExtra("long") ?: "defaultLong"
        val latitudeDouble: Double = lang!!.toDouble() ?: 0.0
        val longitudeDouble: Double = long!!.toDouble() ?: 0.0
        val latLng = LatLng(latitudeDouble, longitudeDouble)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        getLocationForDevice()
        viewModel.getPedagangNearBy(dataUser.accessToken, 0.0, 0.0).observe(this) {result ->
          if(result != null) {
                when (result) {
                    is Result.Success -> {
                        result.data.data?.forEach { data: DataItemPedagangNerby? ->
                           if (data != null) {
                                val latLng = data.latitude?.let { data.longitude?.let { it1 ->
                                    LatLng(it,
                                        it1
                                    )
                                } }
                                mMap.addMarker(
                                    MarkerOptions()
                                        .position(latLng!!)
                                        .title(data.nameMerchant)
                                        .snippet(data.summaryProductPedagang?.nameProduct)
                                )
                            }

                        }
                    }

                    is Result.Loading -> return@observe

                    is Result.Error -> {
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "onMapReady Error: ${result.error}")
                    }
                }
            }else{
                Toast.makeText(applicationContext, "gak ada datanya", Toast.LENGTH_SHORT).show()
            }
        }




        setMapStyle()
    }
    override fun onResume() {
        super.onResume()
        getDataUserLogin()
        observeProduk()
    }
    private fun setupRecyclerView(adapter: ProdukMapAdapter) {
        val layoutManager = LinearLayoutManager(this@MapsPedagangActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }
    private fun getDataUserLogin() {
        loginViewModel.getUserLogin().observe(this) { result ->
            dataUser = result
        }
    }
    private fun observeProduk() {

        viewModel.getPedagangNearBy(dataUser.accessToken, 0.0, 0.0).observe(this) { result ->
            handleProdukResult(result, adapter)
        }
    }

    private fun handleProdukResult(result: Result<PedagangNearByResponse>, adapter: ProdukMapAdapter) {
        when (result) {
            is Result.Loading ->  return
            is Result.Success -> {
                val data = result.data.data
                if (data.isNullOrEmpty()) {
                    showSnackBar("Produk tidak ada")
                } else {
                    adapter.submitList(data)
                }
            }
            is Result.Error -> {
                showSnackBar(result.error)
            }
        }
    }
    private fun showSnackBar(messageResId: Any) {
        SnackbarUtils.showWithDismissAction(binding.root, messageResId.toString())
    }
    private fun getLocationForDevice() {
        if (
            ContextCompat.checkSelfPermission(
                applicationContext, ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                if (it != null) {
                    val latLng = LatLng(it.latitude, it.longitude)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8f))
                } else showToast(
                    applicationContext,
                    "Tolong aktifkan lokasmu"
                )
            }
        } else requestPermissionLauncher.launch(ACCESS_COARSE_LOCATION)
    }
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    private fun setMapStyle() {
        try {
            val success =
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style))
            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (exception: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", exception)
        }
    }

    companion object {
        private const val TAG = "MapsActivity"
    }
}