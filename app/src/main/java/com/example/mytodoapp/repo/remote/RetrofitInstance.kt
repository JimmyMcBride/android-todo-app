package com.example.mytodoapp.repo.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://knex-todo.herokuapp.com/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val todoService: TodoService = retrofit.create(TodoService::class.java)

    val authService: AuthService = retrofit.create(AuthService::class.java)
}