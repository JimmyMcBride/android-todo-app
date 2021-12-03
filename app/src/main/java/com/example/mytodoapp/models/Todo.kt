package com.example.mytodoapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "todos")
@JsonClass(generateAdapter = true)
data class Todo(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "completed") val completed: Boolean,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "updatedAt") val updatedAt: String,
) : Parcelable
