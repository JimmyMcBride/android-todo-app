package com.example.mytodoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mytodoapp.models.AddTodoBody
import com.example.mytodoapp.models.UpdateTodoBody
import com.example.mytodoapp.repo.TodoRepo

class TodoViewModel(private val token: String) : ViewModel() {

    val todos = liveData {
        emit(TodoRepo(token).getAllTodos())
    }

    suspend fun editTodo(id: Int, body: UpdateTodoBody) = TodoRepo(token).updateTodo(id, body)

    suspend fun addTodo(body: AddTodoBody) = TodoRepo(token).addTodo(body)

    suspend fun deleteTodo(id: Int) = TodoRepo(token).deleteTodo(id)

}