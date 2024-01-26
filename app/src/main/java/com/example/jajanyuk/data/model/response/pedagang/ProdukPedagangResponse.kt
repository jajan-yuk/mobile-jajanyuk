package com.example.jajanyuk.data.model.response.pedagang

import com.google.gson.annotations.SerializedName

data class ProdukPedagangResponse(

	@field:SerializedName("data")
	val data: List<DataItemProduk?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class VariantItem(

	@field:SerializedName("count_variant_type")
	val countVariantType: Int? = null,

	@field:SerializedName("total_price")
	val totalPrice: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("variant_types")
	val variantTypes: List<VariantTypesItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Category(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class VariantTypesItem(

	@field:SerializedName("variant_id")
	val variantId: Int? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class DataItemProduk(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("variant")
	val variant: List<VariantItem?>? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: Category? = null
)
