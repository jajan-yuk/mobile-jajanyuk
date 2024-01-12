package com.example.jajanyuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.jajanyuk.databinding.ActivityLoginBinding
import com.example.jajanyuk.databinding.ActivityMainBinding
import com.example.jajanyuk.databinding.ActivitySplashScreenBinding
import com.example.jajanyuk.ui.auth.LoginViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginActivity
import com.example.jajanyuk.ui.auth.login.LoginViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val  loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        val customDialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_logout, null)
        val alertDialog = buildAlertDialog(customDialogView)
        val yesButton = customDialogView.findViewById<Button>(R.id.btnyes)
        val noButton = customDialogView.findViewById<Button>(R.id.btnno)
        yesButton.setOnClickListener {
            handleYesButtonClick()
        }
        noButton.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
    private fun handleYesButtonClick() {
        loginViewModel.deleteUserLogin()
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        finish()
    }
    private fun buildAlertDialog(customDialogView: View): AlertDialog {
        return AlertDialog.Builder(this)
            .setView(customDialogView)
            .create()
    }
}