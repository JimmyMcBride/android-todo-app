package com.example.mytodoapp.repo.remote

import com.example.mytodoapp.models.AddTodoBody
import com.example.mytodoapp.models.DeleteMessage
import com.example.mytodoapp.models.Todo
import com.example.mytodoapp.models.UpdateTodoBody
import retrofit2.Response
import retrofit2.http.*

interface TodoService {
    @POST("todos")
    suspend fun addTodo(@Body() body: AddTodoBody, @HeaderMap headers: Map<String, String>): Todo

    @PUT("todos/{id}")
    suspend fun updateTodo(@Path("id") id: Int, @Body() body: UpdateTodoBody, @HeaderMap headers: Map<String, String>): Todo

    @GET("todos")
    suspend fun getTodos(@HeaderMap headers: Map<String, String>): List<Todo>

    @DELETE("todos/{id}")
    suspend fun deleteTodo(@Path("id") id: Int, @HeaderMap headers: Map<String, String>): Response<DeleteMessage>

}