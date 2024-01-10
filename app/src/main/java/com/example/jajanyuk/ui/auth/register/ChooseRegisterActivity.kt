package com.example.jajanyuk.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityChooseRegisterBinding
import com.example.jajanyuk.databinding.ActivityLoginBinding
import com.example.jajanyuk.ui.auth.login.LoginActivity

class ChooseRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseRegisterBinding
    private var selectedItemId: Int = -1
    private var lastSelectedTextView: TextView? = null
    private var selectedItem: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this@ChooseRegisterActivity, LoginActivity::class.java))
            finish()
        }
        binding.btnDisabled.isEnabled = true

        binding.item1.setOnClickListener {
            binding.btnRegister.visibility = View.VISIBLE
            binding.btnDisabled.visibility = View.GONE
            handleItemClick(binding.item1, binding.tvBuyer, "Buyer")
        }

        binding.item2.setOnClickListener {
            binding.btnRegister.visibility = View.VISIBLE
            binding.btnDisabled.visibility = View.GONE
            handleItemClick(binding.item2, binding.tvSeller, "Seller")
        }


        binding.btnRegister.setOnClickListener {
            if(selectedItem == "Buyer"){
                startActivity(Intent(this@ChooseRegisterActivity, RegisterActivity::class.java))
            }else{
                Toast.makeText(this, "Featured not yed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleItemClick(item: LinearLayout, textView: TextView, vehicleType: String) {
        if (selectedItemId == item.id) {
            selectedItem = null
            return
        }
        if (selectedItemId != -1) {
            resetItemSelection(findViewById(selectedItemId), lastSelectedTextView)
        }
        selectedItemId = item.id
        lastSelectedTextView = textView
        item.isSelected = true
        item.setBackgroundResource(R.drawable.bg_choose_register_active)
        selectedItem = vehicleType
        // binding.coba.text = selectedVehicle
    }

    private fun resetItemSelection(item: LinearLayout, textView: TextView?) {
        item.isSelected = false
        item.setBackgroundResource(R.drawable.bg_choose_register)
        selectedItemId = -1
        lastSelectedTextView = null
    }

}