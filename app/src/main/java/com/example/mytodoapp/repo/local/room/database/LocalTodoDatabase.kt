package com.example.mytodoapp.repo.local.room.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytodoapp.models.Todo
import com.example.mytodoapp.repo.local.room.dao.LocalTodoDao

@Database(entities = [Todo::class], version = 1)
abstract class LocalTodoDatabase : RoomDatabase() {
    abstract fun todoDao(): LocalTodoDao

    companion object {
        private const val DATABASE_NAME = "local_todo_database"

        private lateinit var application: Application
        private val database: LocalTodoDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(application, LocalTodoDatabase::class.java, DATABASE_NAME).build()
        }

        fun getDatabase(application: Application): LocalTodoDatabase {
            this.application = application
            return database
        }
    }
}