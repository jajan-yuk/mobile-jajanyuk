package com.example.jajanyuk.ui.pembeli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jajanyuk.R

class DetailPedagangActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pedagang)
    }
    companion object {
        const val STORY_INTENT_DATA = "STORY_INTENT_DATA"
    }

}