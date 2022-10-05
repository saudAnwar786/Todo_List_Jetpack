package com.sayaa.todolistjetpack.ui.add_edit_todo

sealed class AddEditTodoEvent{
    data class onTitleChanged(val title : String):AddEditTodoEvent()
    data class onDescriptionChanged(val description: String) : AddEditTodoEvent()
    object onSaveTodoClick: AddEditTodoEvent()
}
