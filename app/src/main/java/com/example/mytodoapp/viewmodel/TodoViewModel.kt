package com.example.mytodoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mytodoapp.repo.TodoRepo

class TodoViewModel(token: String) : ViewModel() {

    val todos = liveData {
        emit(TodoRepo(token).getAllTodos())
    }

}