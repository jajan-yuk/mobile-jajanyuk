package com.example.jajanyuk.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityRegisterBinding
import com.example.jajanyuk.ui.auth.AuthViewModelFactory
import com.example.jajanyuk.utils.Result
import com.example.jajanyuk.utils.SnackbarUtils

class RegisterActivity : AppCompatActivity() {
    private val viewModel: RegisterViewModel by viewModels {
        AuthViewModelFactory.getInstance(application)
    }
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
        when {
            password != passwordConfirm || arrayOf(name, username, email, password, passwordConfirm, alamat).any { it.isEmpty() } -> {
                showSnackBar(
                    when {
                        password != passwordConfirm -> R.string.password_notmatch
                        name.isEmpty() -> R.string.fullName_required
                        username.isEmpty() -> R.string.fullName_required
                        email.isEmpty() -> R.string.email_required
                        password.isEmpty() -> R.string.password_required
                        alamat.isEmpty() -> R.string.password_required
                        else -> R.string.confirmPassword_required
                    }
                )
            }
            else -> viewModel.register(name, username, password, email, alamat).observe(this) { result ->
                when (result) {
                    is Result .Loading -> ProgressDialogUtils.showProgressDialog(this@RegisterActivity)
                    is Result.Success -> onRegisterSuccess()
                    is Result.Error -> onRegisterError(result.error)
                }
            }
        }
    }
    private fun showSnackBar(messageResId: Any) {
        SnackbarUtils.showWithDismissAction(binding.root, messageResId)
    }
}


