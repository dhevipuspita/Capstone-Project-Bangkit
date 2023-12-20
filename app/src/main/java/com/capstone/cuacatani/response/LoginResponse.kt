package com.capstone.cuacatani.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("loginResult")
	val loginResult: LoginResult,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class LoginResult(

	@field:SerializedName("username")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

//	@field:SerializedName("token")
//	val token: String? = null
)