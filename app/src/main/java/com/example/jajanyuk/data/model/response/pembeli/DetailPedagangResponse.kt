package com.example.jajanyuk.data.model.response.pembeli

import com.google.gson.annotations.SerializedName

data class DetailPedagangResponse(

	@field:SerializedName("data")
	val data: DataDetailPedagang? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataDetailPedagang(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name_merchant")
	val nameMerchant: String? = null,

	@field:SerializedName("is_active")
	val isActive: Boolean? = null,

	@field:SerializedName("distance")
	val distance: Int? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("latitude")
	val latitude: Any? = null,

	@field:SerializedName("ID")
	val iD: String? = null,

	@field:SerializedName("summary_product_pedagang")
	val summaryProductPedagang: SummaryProductPedagangDetail? = null,

	@field:SerializedName("longitude")
	val longitude: Any? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
)

data class ProductsItem(

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

data class Category(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
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

data class SummaryProductPedagangDetail(

	@field:SerializedName("name_product")
	val nameProduct: String? = null,

	@field:SerializedName("harga")
	val harga: String? = null,

	@field:SerializedName("rating")
	val rating: Int? = null
)
