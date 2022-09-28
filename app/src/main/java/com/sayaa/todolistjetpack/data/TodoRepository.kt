package com.sayaa.todolistjetpack.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface TodoRepository {


    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    fun getTodoById(id: Int):Todo

    fun getAllTodo(): Flow<List<Todo>>
}