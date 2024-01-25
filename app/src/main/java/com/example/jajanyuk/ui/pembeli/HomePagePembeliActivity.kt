package com.example.jajanyuk.ui.pembeli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityHomePagePembeliBinding
import com.example.jajanyuk.databinding.ActivityHomePedagangBinding
import com.example.jajanyuk.ui.pedagang.SettingPedagangActivity
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.jajanyuk.utils.SnackbarUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.provider.Settings
import androidx.appcompat.app.AlertDialog

class HomePagePembeliActivity : AppCompatActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        when {
            it[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> getLocation()
            else -> showSnackBar("Izin lokasi ditolak")
        }
    }
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding: ActivityHomePagePembeliBinding
    private var location: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePagePembeliBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSetting.setOnClickListener {
            val setting = Intent(this, SettingActivity::class.java)
            startActivity(setting)
        }

        binding.btnMap.setOnClickListener {
            val map = Intent(this, MapsPedagangActivity::class.java)
            startActivity(map)
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (checkLocationPermission()) {
            getLocation()
        } else {
            requestLocationPermission()
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
                    showSnackBar("langtitudde ${location!!.latitude} dan logntidue ${location!!.longitude}")
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