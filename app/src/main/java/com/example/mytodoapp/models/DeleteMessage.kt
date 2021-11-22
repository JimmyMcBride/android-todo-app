package com.example.mytodoapp.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteMessage(
    val message: String
)
