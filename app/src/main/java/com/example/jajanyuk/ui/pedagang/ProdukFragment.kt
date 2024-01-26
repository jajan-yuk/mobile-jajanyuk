package com.example.jajanyuk.ui.pedagang

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import com.example.jajanyuk.R
import com.example.jajanyuk.data.model.response.DataUser
import com.example.jajanyuk.databinding.FragmentProdukBinding
import com.example.jajanyuk.ui.auth.LoginViewModelFactory
import com.example.jajanyuk.ui.auth.login.LoginViewModel
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jajanyuk.ui.adapter.ProdukPedagangAdapter
import com.example.jajanyuk.ui.pembeli.HomePagePembeliActivity
import com.example.jajanyuk.ui.splash.SplashScreenViewModel
import com.example.jajanyuk.utils.Result
import com.example.jajanyuk.utils.SnackbarUtils

class ProdukFragment : Fragment() {

    private var _binding: FragmentProdukBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataUser: DataUser
    private val  loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(requireActivity().application)
    }

    private val  viewModel: ProductPedagangViewModel by viewModels {
        ProdukPedagangViewModelFactory.getInstance(requireActivity().application)
    }
    private val userViewModel: SplashScreenViewModel by viewModels {
        LoginViewModelFactory.getInstance(requireActivity().application)
    }

    private val adapter = ProdukPedagangAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentProdukBinding.inflate(inflater, container, false)
         setupRecyclerView(adapter)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupViews()

        loginViewModel.getUserLogin().observe(this) {
            viewModel.getProductByPedagang(it.accessToken).observe(this) {
                when (it) {
                    is Result.Loading -> return@observe
                    is Result.Success -> {
                        val data = it.data.data
                        if (data.isNullOrEmpty()) {
                            showSnackBar("Produk tidak ada")
                        } else {
                            adapter.submitList(data)
                        }
                    }

                    is Result.Error -> "Terjadi eror coba lagi"
                }
            }
        }




    }
    private fun showSnackBar(messageResId: Any) {
        SnackbarUtils.showWithDismissAction(binding.root, messageResId.toString())
    }
    private fun setupViews() {
        loginViewModel.getUserLogin().observe(viewLifecycleOwner) {
            dataUser = it
        }
    }
    private fun setupRecyclerView(adapter: ProdukPedagangAdapter) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvLeaderboard.layoutManager = layoutManager
        binding.rvLeaderboard.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}