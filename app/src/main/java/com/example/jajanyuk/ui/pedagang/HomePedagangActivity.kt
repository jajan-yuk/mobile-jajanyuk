package com.example.jajanyuk.ui.pedagang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityHomePedagangBinding
import com.example.jajanyuk.databinding.ActivitySplashScreenBinding

class HomePedagangActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePedagangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePedagangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinnerItems = resources.getStringArray(R.array.spinner_items)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinner.adapter = adapter
        binding.spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })

        binding.btnPengaturan.setOnClickListener {
            val pengaturan = Intent(this,  SettingPedagangActivity::class.java)
            startActivity(pengaturan)
        }
    }
}