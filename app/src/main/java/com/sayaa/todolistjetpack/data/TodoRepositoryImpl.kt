package com.sayaa.todolistjetpack.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val todoDao: TodoDao
):TodoRepository {
    override suspend fun insertTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo  )
    }

    override fun getTodoById(id: Int): Todo {
        return todoDao.getTodoById(id)
    }

    override fun getAllTodo(): Flow<List<Todo>> {
        return todoDao.getAllTodo()
    }
}