package com.example.jajanyuk.ui.auth.register

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityRegisterBinding
import com.example.jajanyuk.ui.auth.AuthViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginActivity
import com.example.jajanyuk.utils.ProgressDialogUtils
import com.example.jajanyuk.utils.Result
import com.example.jajanyuk.utils.SnackbarUtils
import com.google.android.material.button.MaterialButton

class RegisterActivity : AppCompatActivity() {
    private val viewModel: RegisterViewModel by viewModels {
        AuthViewModelFactory.getInstance(application)
    }
    private var emailUser: String? = null
    private var passwordUser: String? = null
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.btnRegister.setOnClickListener { handleRegister() }


    }

    private fun handleRegister() {
        val (name, username, email, alamat) = binding.run {
            arrayOf(
                etFullname.text.toString().trim(),
                etUsername.text.toString().trim(),
                etEmail.text.toString().trim(),
                etAddress.text.toString().trim(),
            )
        }
        val (password, passwordConfirm) = binding.run {
            arrayOf(
                etPassword.text.toString().trim(),
                etConfirmPassword.text.toString().trim(),
            )
        }
        emailUser = email
        when {
            password != passwordConfirm || arrayOf(name, username, email, password, passwordConfirm, alamat).any { it.isEmpty() } -> {
                showSnackBar(
                    when {
                        password != passwordConfirm -> R.string.password_notmatch
                        name.isEmpty() -> R.string.fullName_required
                        username.isEmpty() -> R.string.username_required
                        email.isEmpty() -> R.string.email_required
                        password.isEmpty() -> R.string.password_required
                        alamat.isEmpty() -> R.string.password_required
                        else -> R.string.confirmPassword_required
                    }
                )
            }
            else -> viewModel.register(name, username, password, email, alamat).observe(this) { result ->
                when (result) {
                    is Result.Loading -> ProgressDialogUtils.showProgressDialog(this@RegisterActivity)
                    is Result.Success -> onRegisterSuccess()
                    is Result.Error -> onRegisterError(result.error)
                }
            }
        }
    }
    private fun onRegisterSuccess() {
        ProgressDialogUtils.hideProgressDialog()
        val customDialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_register_success, null)
        val alertDialog = AlertDialog.Builder(this)
            .setView(customDialogView)
            .create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()

        val tvDescription = customDialogView.findViewById<TextView>(R.id.tv_description)
        tvDescription.text = emailUser
        val btnLogin = customDialogView.findViewById<MaterialButton>(R.id.btn_login)
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            alertDialog.dismiss()
            finish()
        }
    }

    private fun onRegisterError(errorMessage: String) {
        ProgressDialogUtils.hideProgressDialog()
        showSnackBar(errorMessage)
    }
    private fun showSnackBar(messageResId: Any) {
        SnackbarUtils.showWithDismissAction(binding.root, messageResId)
    }
}


