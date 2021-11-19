package com.example.mytodoapp.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterBody(
    val username: String,
    val email: String,
    val password: String
)
