package com.example.jajanyuk.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.jajanyuk.databinding.ActivityLoginBinding
import android.text.Editable

import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import com.example.jajanyuk.MainActivity
import com.example.jajanyuk.R
import com.example.jajanyuk.ui.auth.AuthViewModelFactory
import com.example.jajanyuk.ui.auth.LoginViewModelFactory
import com.example.jajanyuk.ui.auth.register.ChooseRegisterActivity
import com.example.jajanyuk.ui.pedagang.HomePedagangActivity
import com.example.jajanyuk.ui.pembeli.HomePagePembeliActivity
import com.example.jajanyuk.utils.ProgressDialogUtils
import com.example.jajanyuk.utils.SnackbarUtils
import com.example.jajanyuk.utils.Result


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvLoginToRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ChooseRegisterActivity::class.java))
        }
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                validatePassword(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val (username, password) = binding.run {
            arrayOf(
                etUsername.text.toString().trim(),
                etPassword.text.toString().trim()
            )
        }
        when {
            arrayOf(username, password).any {
                it.isEmpty()
            } -> {
                showSnackBar(
                    when {
                        username.isEmpty() -> R.string.username_required
                        else -> R.string.password_required
                    }
                )
            }
            else -> viewModel.login(username, password).observe(this@LoginActivity) { result ->
                when (result) {
                    is Result.Loading -> ProgressDialogUtils.showProgressDialog(this@LoginActivity)
                    is Result.Success -> onLoginSuccess()
                    is Result.Error -> onLoginError(result.error)
                }
            }
        }
    }

    private fun onLoginSuccess() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getUserLogin().observe(this) {
                if (it.accessToken.isNotEmpty()) {
                    var role = it.user?.role?.name.toString()
                    Toast.makeText(this, role, Toast.LENGTH_SHORT).show()
                    if(role == "USER") {
                        Handler(Looper.getMainLooper()).postDelayed({
                            val homePembeliIntent = Intent(this,   HomePagePembeliActivity::class.java)
                            startActivity(homePembeliIntent)
                            finish()
                        }, 3000)

                    } else{

                        Handler(Looper.getMainLooper()).postDelayed({
                            val homePedagangIntent = Intent(this,  HomePedagangActivity::class.java)
                            startActivity(homePedagangIntent)
                            finish()
                        }, 3000)
                    }
                }
            }
        }, 3000L)
    }

    private fun onLoginError(errorMessage: String) {
        ProgressDialogUtils.hideProgressDialog()
        showSnackBar(errorMessage)
    }
    private fun showSnackBar(messageResId: Any) {
        SnackbarUtils.showWithDismissAction(binding.root, messageResId)
    }

    private fun validatePassword(password: String) {
        if (password.length < 8) {
            binding.passordEditTextLayout.error = "Password must be at least 8 characters long"
        } else {
            binding.passordEditTextLayout.error = null
        }
    }

}