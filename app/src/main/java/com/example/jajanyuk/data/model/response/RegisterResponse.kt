
import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("data")
	val data: DataRegister? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Role(

	@field:SerializedName("id")
	val id: String? = null
)

data class ActivatedAt(

	@field:SerializedName("Valid")
	val valid: Boolean? = null,

	@field:SerializedName("Time")
	val time: String? = null
)

data class DataRegister(

	@field:SerializedName("activated_at")
	val activatedAt: ActivatedAt? = null,

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

	@field:SerializedName("date_of_birthday")
	val dateOfBirthday: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
