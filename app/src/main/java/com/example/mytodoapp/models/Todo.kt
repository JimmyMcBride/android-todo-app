package com.example.mytodoapp.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Todo(
    val id: Int,
    val title: String,
    val description: String?,
    val completed: Boolean,
    val date: String,
    val updatedAt: String,
) : Parcelable
