package com.example.jajanyuk.data.model.response.pembeli

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PedagangNearByResponse(

	@field:SerializedName("data")
	val data: List<DataItemPedagangNerby?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

@Parcelize
data class DataItemPedagangNerby(

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
	val latitude: Double? = null,

	@field:SerializedName("ID")
	val iD: String? = null,

	@field:SerializedName("summary_product_pedagang")
	val summaryProductPedagang: SummaryProductPedagang? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
): Parcelable

@Parcelize
data class SummaryProductPedagang(

	@field:SerializedName("name_product")
	val nameProduct: String? = null,

	@field:SerializedName("harga")
	val harga: String? = null,

	@field:SerializedName("rating")
	val rating: Double? = null
): Parcelable
