package com.example.mytodoapp.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginBody(
    val username: String,
    val password: String
)
