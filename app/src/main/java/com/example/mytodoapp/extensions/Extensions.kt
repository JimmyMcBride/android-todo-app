package com.example.mytodoapp.extensions

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

fun String.logMe(tag: String = "TODO_APP") {
    Log.d(tag, this)
}

val ViewGroup.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)