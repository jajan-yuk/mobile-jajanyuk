package com.example.jajanyuk.ui.pedagang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jajanyuk.R
import com.example.jajanyuk.data.model.response.DataUser
import com.example.jajanyuk.databinding.FragmentProdukBinding

class ProdukFragment : Fragment() {

    private var _binding: FragmentProdukBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataUser: DataUser



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentProdukBinding.inflate(inflater, container, false)
        // setupRecyclerView(adapter)
        return binding.root
    }


}