package com.example.jajanyuk.ui.pedagang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityHomePedagangBinding
import com.example.jajanyuk.databinding.ActivitySettingPedagangBinding
import com.example.jajanyuk.ui.auth.LoginViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginActivity
import com.example.jajanyuk.ui.auth.login.LoginViewModel
import com.example.jajanyuk.utils.SnackbarUtils

class SettingPedagangActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingPedagangBinding
    private val  loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingPedagangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }

        binding.btnProfile.setOnClickListener {
            showSnackBar("Fitur belum ada")
        }

        binding.btnRiwayat.setOnClickListener {
            showSnackBar("Fitur belum ada")
        }
    }
    private fun showSnackBar(messageResId: Any) {
        SnackbarUtils.showWithDismissAction(binding.root, messageResId)
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
        startActivity(Intent(this@SettingPedagangActivity, LoginActivity::class.java))
        finish()
        finish()
    }
    private fun buildAlertDialog(customDialogView: View): AlertDialog {
        return AlertDialog.Builder(this)
            .setView(customDialogView)
            .create()
    }

}