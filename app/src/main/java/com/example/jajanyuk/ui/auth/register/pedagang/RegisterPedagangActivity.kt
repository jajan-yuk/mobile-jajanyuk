package com.example.jajanyuk.ui.auth.register.pedagang

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityChooseRegisterBinding
import com.example.jajanyuk.databinding.ActivityRegisterBinding
import com.example.jajanyuk.databinding.ActivityRegisterPedagangBinding
import com.example.jajanyuk.ui.auth.AuthViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginActivity
import com.example.jajanyuk.ui.auth.register.pembeli.RegisterViewModel
import com.example.jajanyuk.utils.ProgressDialogUtils
import com.example.jajanyuk.utils.Result
import com.example.jajanyuk.utils.SnackbarUtils
import com.example.jajanyuk.utils.reduceFileImage
import com.example.jajanyuk.utils.uriToFile
import com.google.android.material.button.MaterialButton
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class RegisterPedagangActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterPedagangBinding
    private var currentImageUri: Uri? = null
    private var fullname: String? = null
    private var email: String? = null
    private var password: String? = null
    private val viewModel: RegisterPedagangViewModel by viewModels {
        AuthViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPedagangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.btnChoose.setOnClickListener {
            startGallery()
        }
        extractDataFromIntent()
        binding.btnSignup.setOnClickListener {
            handleRegister()
        }
    }
    private fun handleRegister() {

        Log.e("asdasd", currentImageUri.toString())
        var (name, profile, telepon, alamat) = binding.run {
            arrayOf(
                etFullname.text.toString().trim(),
                etNamaProfile.text.toString().trim(),
                etNoTelepon.text.toString().trim(),
                etAlamat.text.toString().trim(),
            )
        }
        when {
            arrayOf(name,profile, telepon, alamat).any { it.isEmpty() } -> {
                showSnackBar(
                    when {
                        name.isEmpty() -> "Nama merchant tidak boleh kosong"
                        profile.isEmpty() -> "Nama profile tidak boleh kosong"
                        alamat.isEmpty() -> "Alamat tidak boleh kosong"
                        else -> "No telepon tidak boleh kosong"
                    }
                )
            }
            else ->
                currentImageUri?.let { uri ->
                    val imageFile = uriToFile(uri, this)
                    Log.d("Image File", "showImage: ${imageFile.path}")
                    Log.d("nameee", name)


                    var name1 = name.toString().toRequestBody("text/plain".toMediaType())
                    var  username = fullname!!.toString().toRequestBody("text/plain".toMediaType())
                    var  password2 = password!!.toString().toRequestBody("text/plain".toMediaType())
                    var  email2 = email!!.toString().toRequestBody("text/plain".toMediaType())
                    var  alamat2 = alamat!!.toString().toRequestBody("text/plain".toMediaType())
                    var  profile2 = profile.toString().toRequestBody("text/plain".toMediaType())
                    var  telepon2 = telepon.toString().toRequestBody("text/plain".toMediaType())
                    viewModel.registerPedagang(name1, username, password2, email2,  alamat2, imageFile, profile2, telepon2).observe(this) { result ->
                        when (result) {
                            is Result.Loading -> ProgressDialogUtils.showProgressDialog(this@RegisterPedagangActivity)
                            is Result.Success -> onRegisterSuccess()
                            is Result.Error -> onRegisterError(result.error)
                        }
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
        tvDescription.text = email
        val btnLogin = customDialogView.findViewById<MaterialButton>(R.id.btn_login)
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            alertDialog.dismiss()
            finish()
        }
    }

    private fun onRegisterError(errorMessage: String) {
        Log.e("eror register", errorMessage)
        ProgressDialogUtils.hideProgressDialog()
        showSnackBar(errorMessage)
    }


    private fun showSnackBar(messageResId: Any) {
        SnackbarUtils.showWithDismissAction(binding.root, messageResId)
    }
    private fun extractDataFromIntent() {
        fullname = intent.getStringExtra("fullname") ?: ""
        email = intent.getStringExtra("email") ?: ""
        password = intent.getStringExtra("password") ?: ""
    }

    private fun startGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.type = "image/*"
        launcherGallery.launch(galleryIntent)
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            data?.let {
                val selectedImageUri: Uri? = it.data
                if (selectedImageUri != null) {
                    currentImageUri = selectedImageUri
                    showImage()
                } else {
                    Log.d("Photo Picker", "No media selected")
                }
            }
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }
}