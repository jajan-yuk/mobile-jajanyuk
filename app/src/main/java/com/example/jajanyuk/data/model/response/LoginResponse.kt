package com.example.jajanyuk.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class LoginResponse(

	@field:SerializedName("data")
	val data: DataUser,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

@Parcelize
data class User(

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

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
): Parcelable

@Parcelize
data class Role(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
): Parcelable

@Parcelize
data class DataUser(

	@field:SerializedName("access_token")
	val accessToken: String,

	@field:SerializedName("refresh_token")
	val refreshToken: String,

	@field:SerializedName("user")
	val user: User? = null
): Parcelable
