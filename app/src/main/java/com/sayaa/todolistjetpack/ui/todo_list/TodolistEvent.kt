package com.sayaa.todolistjetpack.ui.todo_list

import com.sayaa.todolistjetpack.data.Todo

sealed class TodolistEvent() {

    data class onDeleteTodo(val todo: Todo):TodolistEvent()
    object onUndoDeleteClick:TodolistEvent()

    data class onTodoclick(val todo: Todo):TodolistEvent()
    object onAddTodoClick:TodolistEvent()
    data class onDoneChangeClick(
        val todo: Todo,
        val isDone:Boolean
    ):TodolistEvent()
}