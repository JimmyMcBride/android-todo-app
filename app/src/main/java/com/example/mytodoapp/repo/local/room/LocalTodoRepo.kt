package com.example.mytodoapp.repo.local.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.mytodoapp.extensions.logMe
import com.example.mytodoapp.models.Todo
import com.example.mytodoapp.repo.local.room.database.LocalTodoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext



class LocalTodoRepo(private val application: Application) {
    private val todoDao by lazy {
        LocalTodoDatabase.getDatabase(application).todoDao()
    }

    fun getAllTodos() = todoDao.getAllLocalTodos()
    fun getCompletedTodos() = todoDao.getCompletedLocalTodos()
    fun getUncompletedTodos() = todoDao.getUncompletedLocalTodos()

//    suspend fun getTodos(sortingMethod: Sort?, filterMethod: String?): Flow<List<Todo>> {
//        return withContext(Dispatchers.IO) {
//            when (sortingMethod) {
//                Sort.ID_ASC -> todoDao.getTodosFilterSort()
//                Sort.ID_DESC -> todoDao.getTodosFilterSort("id desc", filterMethod)
//                Sort.TITLE_ASC -> todoDao.getTodosFilterSort("title asc", filterMethod)
//                Sort.TITLE_DESC -> todoDao.getTodosFilterSort("title desc", filterMethod)
//                Sort.UPDATED_ASC -> todoDao.getTodosFilterSort("updatedAt asc", filterMethod)
//                Sort.UPDATED_DESC -> todoDao.getTodosFilterSort("updatedAt desc", filterMethod)
//                else -> todoDao.getAllLocalTodos()
//            }
//        }
//    }
//
//
//    suspend fun sortBy(sortingMethod: Sort?): Flow<List<Todo>> {
//       return withContext(Dispatchers.IO) {
//           when (sortingMethod) {
//               Sort.ID_ASC -> todoDao.getAllLocalTodosByIdAsc()
//               Sort.ID_DESC -> todoDao.getAllLocalTodosByIdDesc()
//               Sort.TITLE_ASC -> todoDao.getAllLocalTodosByTitleAsc()
//               Sort.TITLE_DESC -> todoDao.getAllLocalTodosByTitleDesc()
//               Sort.UPDATED_ASC -> todoDao.getAllLocalTodosByUpdatedAsc()
//               Sort.UPDATED_DESC -> todoDao.getAllLocalTodosByUpdatedDesc()
//               else -> todoDao.getAllLocalTodos()
//           }
//       }
//    }

    suspend fun addTodo(todo: Todo) {
        withContext(Dispatchers.IO) {
            todoDao.insertLocalTodo(todo)
        }
    }

    suspend fun deleteTodo(todo: Todo) {
        withContext(Dispatchers.IO) {
            todoDao.deleteLocalTodo(todo)
        }
    }
}