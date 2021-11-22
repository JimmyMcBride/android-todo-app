package com.example.mytodoapp.repo

import com.example.mytodoapp.models.AddTodoBody
import com.example.mytodoapp.models.DeleteMessage
import com.example.mytodoapp.models.Todo
import com.example.mytodoapp.models.UpdateTodoBody
import com.example.mytodoapp.repo.remote.RetrofitInstance
import retrofit2.Response

class TodoRepo(token: String) {
    private val headers = mapOf(Pair("Authorization", token))

    suspend fun getAllTodos(): List<Todo> {
        return RetrofitInstance.todoService.getTodos(headers)
    }

    suspend fun updateTodo(id: Int, body: UpdateTodoBody): Todo {
        return RetrofitInstance.todoService.updateTodo(id, body, headers)
    }

    suspend fun addTodo(body: AddTodoBody): Todo {
        return RetrofitInstance.todoService.addTodo(body, headers)
    }

    suspend fun deleteTodo(id: Int): Response<DeleteMessage> {
        return RetrofitInstance.todoService.deleteTodo(id, headers)
    }
}