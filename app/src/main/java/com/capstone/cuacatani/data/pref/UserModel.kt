package com.capstone.cuacatani.data.pref

data class UserModel(
    val email: String,
    val token: String,

    //diluar data
    val isLogin: Boolean = false
)