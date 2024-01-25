package com.example.jajanyuk.data.model.response.auth

import com.google.gson.annotations.SerializedName

data class RegisterPedagangResponse(

	@field:SerializedName("data")
	val data: DataRegisterPedagang? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataRegisterPedagang(

	@field:SerializedName("activated_at")
	val activatedAt: ActivatedAt? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("pedagang")
	val pedagang: DataPedagang? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("role")
	val role: RolePedagang? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("date_of_birthday")
	val dateOfBirthday: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class RolePedagang(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class ActivatedAt(

	@field:SerializedName("Valid")
	val valid: Boolean? = null,

	@field:SerializedName("Time")
	val time: String? = null
)

data class DataPedagang(

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
	val latitude: Int? = null,

	@field:SerializedName("ID")
	val iD: String? = null,

	@field:SerializedName("longitude")
	val longitude: Int? = null
)
