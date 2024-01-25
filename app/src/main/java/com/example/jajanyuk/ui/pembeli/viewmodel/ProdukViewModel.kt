package com.example.jajanyuk.ui.pembeli.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jajanyuk.data.repository.LoginRepository
import com.example.jajanyuk.data.repository.PedagangRepository

class ProdukViewModel (private val pedagangRepository: PedagangRepository) : ViewModel()  {
    fun getPedagangNearBy(token: String, langtitude: Double, longtitude: Double) = pedagangRepository.getPedagangNearBy(token, langtitude, longtitude)
    fun getSearchPedagang(token: String, keyword: String) = pedagangRepository.getSearchPedagang(token, keyword)
    fun getDetailPedagang(token: String, id: String) = pedagangRepository.getPedagangDetail(token, id)
}