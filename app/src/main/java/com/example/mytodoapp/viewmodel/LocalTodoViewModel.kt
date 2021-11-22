package com.example.mytodoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mytodoapp.repo.local.room.LocalTodoRepo
import com.example.mytodoapp.repo.local.room.model.LocalTodo
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class LocalTodoViewModel(
    private val localTodoRepo: LocalTodoRepo
) : ViewModel() {
    val todos = localTodoRepo.getAllTodos().asLiveData(viewModelScope.coroutineContext)

    fun addTodo(todo: LocalTodo) {
        viewModelScope.launch {
            localTodoRepo.addTodo(todo)
        }
    }

    fun deleteTodo(todo: LocalTodo) {
        viewModelScope.launch {
            localTodoRepo.deleteTodo(todo)
        }
    }

    class Factory(
        private val localTodoRepo: LocalTodoRepo
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LocalTodoViewModel::class.java)) {
                return LocalTodoViewModel(localTodoRepo) as T
            } else {
                throw IllegalArgumentException("Cannot create instance of ContactViewModel")
            }
        }
    }
}