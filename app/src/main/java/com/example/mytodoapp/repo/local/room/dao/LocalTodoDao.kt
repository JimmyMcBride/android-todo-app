package com.example.mytodoapp.repo.local.room.dao

import androidx.room.*
import com.example.mytodoapp.repo.local.room.model.LocalTodo
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalTodoDao {
    @Query("SELECT * FROM todos")
    fun getAllLocalTodos(): Flow<List<LocalTodo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocalTodo(todo: LocalTodo)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAllLocalTodos(todos: List<LocalTodo>)

    @Delete
    fun deleteLocalTodo(todo: LocalTodo)
}