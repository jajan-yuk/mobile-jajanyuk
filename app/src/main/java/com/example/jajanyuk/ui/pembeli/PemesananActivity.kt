package com.example.jajanyuk.ui.pembeli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityPemesananBinding
import com.example.jajanyuk.databinding.ActivityRingkasanPembelianBinding
import com.example.jajanyuk.ui.auth.login.LoginActivity

class PemesananActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPemesananBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDialog()
    }


    private fun showDialog() {
        val customDialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_pemesanan, null)
        val alertDialog = buildAlertDialog(customDialogView)
        val noButton = customDialogView.findViewById<Button>(R.id.btn_back)

        noButton.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    private fun buildAlertDialog(customDialogView: View): AlertDialog {
        return AlertDialog.Builder(this)
            .setView(customDialogView)
            .create()
    }
}