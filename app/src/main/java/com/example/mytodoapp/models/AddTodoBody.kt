package com.example.mytodoapp.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddTodoBody(
    val title: String,
    val description: String?,
    val completed: Boolean?,
)
