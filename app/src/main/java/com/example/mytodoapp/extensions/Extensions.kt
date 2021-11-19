package com.example.mytodoapp.extensions

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.logMe(tag: String = "TODO_APP") {
    Log.d(tag, this)
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.convertToDateTimeStamp(): String {

    val secondApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val timestamp = this.toLong() // timestamp in Long

    val timestampAsDateString = DateTimeFormatter.ISO_INSTANT
        .format(Instant.ofEpochSecond(timestamp))

    val date = LocalDate.parse(timestampAsDateString, secondApiFormat)
    return "${date.month} ${date.dayOfMonth}, ${date.year}"
}

val ViewGroup.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)