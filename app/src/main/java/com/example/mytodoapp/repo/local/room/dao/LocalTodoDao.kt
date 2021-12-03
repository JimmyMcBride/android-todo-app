package com.example.mytodoapp.repo.local.room.dao

import androidx.room.*
import com.example.mytodoapp.models.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalTodoDao {

    @Query("SELECT * FROM todos order by id asc")
    fun getAllLocalTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todos where completed = 1 order by id asc")
    fun getCompletedLocalTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todos where completed = 0 order by id asc")
    fun getUncompletedLocalTodos(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocalTodo(todo: Todo)

    @Delete
    fun deleteLocalTodo(todo: Todo)
}