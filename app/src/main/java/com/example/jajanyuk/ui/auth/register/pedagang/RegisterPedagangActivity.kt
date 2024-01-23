package com.example.jajanyuk.ui.auth.register.pedagang

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityChooseRegisterBinding
import com.example.jajanyuk.databinding.ActivityRegisterBinding
import com.example.jajanyuk.databinding.ActivityRegisterPedagangBinding

class RegisterPedagangActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterPedagangBinding
    private var currentImageUri: Uri? = null

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