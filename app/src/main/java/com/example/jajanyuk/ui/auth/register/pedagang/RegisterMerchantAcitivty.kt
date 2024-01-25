package com.example.jajanyuk.ui.auth.register.pedagang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityRegisterMerchantAcitivtyBinding
import com.example.jajanyuk.databinding.ActivityRegisterPedagangBinding
import com.example.jajanyuk.ui.auth.register.pembeli.RegisterActivity
import com.example.jajanyuk.utils.SnackbarUtils

class RegisterMerchantAcitivty : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterMerchantAcitivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterMerchantAcitivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.btnNext.setOnClickListener {
            if(validateInputs()){
                val intent = Intent(this@RegisterMerchantAcitivty, RegisterPedagangActivity::class.java)
                intent.putExtra("fullname", binding.etFullname.text.toString())
                intent.putExtra("email", binding.etEmail.text.toString())
                intent.putExtra("password", binding.etPassword.text.toString())
                startActivity(intent)
            }
        }

    }
    private fun validateInputs(): Boolean {
        val fullname = binding.etFullname.text.toString()
        val email =binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        if (fullname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showSnackBar("All fields must be filled.")
            return false
        }
        if (password.length < 8) {
            showSnackBar("Password must be at least 8 characters.")
            return false
        }
        if (password != confirmPassword) {
            showSnackBar("Password harus sama")
            return false
        }
        return true
    }

    private fun showSnackBar(messageResId: Any) {
        SnackbarUtils.showWithDismissAction(binding.root, messageResId)
    }
}