package com.example.jajanyuk.ui.pedagang

import androidx.lifecycle.ViewModel
import com.example.jajanyuk.data.repository.ProductRepository

class ProductPedagangViewModel (private val productRepository: ProductRepository) : ViewModel()  {
   fun getProductByPedagang( id: String) = productRepository.getProductByUser(id, "Secret_KEY_JAJAN-YUK")
}