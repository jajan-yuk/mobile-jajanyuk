package com.example.jajanyuk.data.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("data")
	val data: DataUsers? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataUsers(

	@field:SerializedName("user")
	val user: UserData? = null
)

data class Role(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class UserData(

	@field:SerializedName("activated_at")
	val activatedAt: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("role")
	val role: Role? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("address")
	val alamat: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
