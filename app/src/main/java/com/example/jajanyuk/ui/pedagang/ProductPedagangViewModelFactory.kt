package com.example.jajanyuk.ui.pedagang

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jajanyuk.data.repository.PedagangRepository
import com.example.jajanyuk.data.repository.ProductRepository
import com.example.jajanyuk.di.Injection
import com.example.jajanyuk.ui.pembeli.viewmodel.ProdukViewModel

class ProdukPedagangViewModelFactory private constructor(private val productRepository: ProductRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>):  T =
        when {
            modelClass.isAssignableFrom(ProductPedagangViewModel::class.java) ->
                ProductPedagangViewModel(productRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    companion object {
        @Volatile
        private var instance: ProdukPedagangViewModelFactory? = null
        fun getInstance(application: Application): ProdukPedagangViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ProdukPedagangViewModelFactory(Injection.provideProductRepository(application))
            }.also { instance = it }
    }
}