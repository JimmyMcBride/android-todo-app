package com.example.mytodoapp.repo

import com.example.mytodoapp.models.Todo
import com.example.mytodoapp.repo.remote.RetrofitInstance

class TodoRepo(token: String) {
    private val headers = mapOf(Pair("Authorization", token))

    suspend fun getAllTodos(): List<Todo> {
        return RetrofitInstance.todoService.getTodos(headers)
    }
}