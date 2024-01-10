package com.example.jajanyuk.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityChooseRegisterBinding
import com.example.jajanyuk.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, ChooseRegisterActivity::class.java))
            finish()
        }
    }
}