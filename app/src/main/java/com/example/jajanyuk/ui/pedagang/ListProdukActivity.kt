package com.example.jajanyuk.ui.pedagang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.jajanyuk.R
import com.example.jajanyuk.databinding.ActivityHomePedagangBinding
import com.example.jajanyuk.databinding.ActivityListProdukBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ListProdukActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListProdukBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPagerAndTabLayout()
    }

    private fun setupViewPagerAndTabLayout() {
        val viewPager: ViewPager2 = binding?.viewPager ?: return
        val adapter = ListProdukAdapter(this)
        adapter.addFragment(ProdukFragment())
        adapter.addFragment(KategoriFragment())
        adapter.addFragment(VariantFragment())
        viewPager.adapter = adapter

        val tabLayout: TabLayout = binding?.tabs ?: return
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Produk"
                1 -> "Kategori"
                2 -> "Varian"
                else -> ""
            }
        }.attach()
    }
}