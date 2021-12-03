package com.example.mytodoapp.viewmodel

import androidx.lifecycle.*
import com.example.mytodoapp.extensions.logMe
import com.example.mytodoapp.models.Todo
import com.example.mytodoapp.repo.local.room.LocalTodoRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

enum class Sort {
    ID_ASC,
    ID_DESC,
    TITLE_ASC,
    TITLE_DESC,
    UPDATED_ASC,
    UPDATED_DESC,
}

enum class Filter {
    TODO_COMPLETED,
    TODO_UNCOMPLETED,
    NO_FILTER
}

class LocalTodoViewModel(
    private val localTodoRepo: LocalTodoRepo
) : ViewModel() {
    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> get() = _todos

    private val _viewableTodos = MutableLiveData<List<Todo>>()
    val viewableTodos: LiveData<List<Todo>> get() = _viewableTodos

    init {
        viewModelScope.launch {
            localTodoRepo.getAllTodos().collect { todos ->
                todos.toString().logMe()
                _todos.value = todos
                _viewableTodos.value = todos
            }

        }
    }

    fun filterTodos(filterMethod: Filter) {
        viewModelScope.launch {
            when (filterMethod) {
                Filter.TODO_COMPLETED -> _viewableTodos.value = todos.value?.filter { it.completed }
                Filter.TODO_UNCOMPLETED -> _viewableTodos.value = todos.value?.filter { !it.completed }
                Filter.NO_FILTER -> _viewableTodos.value = todos.value
            }
        }
    }

    fun sortTodos(sortingMethod: Sort) {
            when (sortingMethod) {
                Sort.ID_ASC -> {
                    _todos.value = todos.value?.sortedBy { it.id }
                    _viewableTodos.value = viewableTodos.value?.sortedBy { it.id }
                }
                Sort.ID_DESC -> {
                    _todos.value = todos.value?.sortedByDescending { it.id }
                    _viewableTodos.value = viewableTodos.value?.sortedByDescending { it.id }
                }
                Sort.TITLE_ASC -> {
                    _todos.value = todos.value?.sortedBy { it.title }
                    _viewableTodos.value = viewableTodos.value?.sortedBy { it.title }
                }
                Sort.TITLE_DESC -> {
                    _todos.value = todos.value?.sortedByDescending { it.title }
                    _viewableTodos.value = viewableTodos.value?.sortedByDescending { it.title }
                }
                Sort.UPDATED_ASC -> {
                    _todos.value = todos.value?.sortedBy { it.updatedAt }
                    _viewableTodos.value = viewableTodos.value?.sortedBy { it.updatedAt }
                }
                Sort.UPDATED_DESC -> {
                    _todos.value = todos.value?.sortedByDescending { it.updatedAt }
                    _viewableTodos.value = viewableTodos.value?.sortedByDescending { it.updatedAt }
                }
            }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            localTodoRepo.addTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
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