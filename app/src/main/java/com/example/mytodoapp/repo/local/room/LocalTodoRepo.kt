package com.example.mytodoapp.repo.local.room

import android.app.Application
import com.example.mytodoapp.models.Todo
import com.example.mytodoapp.repo.local.room.database.LocalTodoDatabase
import com.example.mytodoapp.repo.local.room.model.LocalTodo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class LocalTodoRepo(private val application: Application) {
    private val todoDao by lazy {
        LocalTodoDatabase.getDatabase(application).todoDao()
    }

    fun getAllTodos() = todoDao.getAllLocalTodos().flowOn(Dispatchers.IO)

    suspend fun addTodo(todo: LocalTodo) {
        withContext(Dispatchers.IO) {
            todoDao.insertLocalTodo(todo)
        }
    }

//    suspend fun updateAllTodos(todos: List<LocalTodo>) {
//        withContext(Dispatchers.IO) {
//            todoDao.insertAllLocalTodos(todos)
//        }
//    }

    suspend fun deleteTodo(todo: LocalTodo) {
        withContext(Dispatchers.IO) {
            todoDao.deleteLocalTodo(todo)
        }
    }
}