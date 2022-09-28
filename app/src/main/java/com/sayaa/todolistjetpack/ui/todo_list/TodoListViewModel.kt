package com.sayaa.todolistjetpack.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayaa.todolistjetpack.data.Todo
import com.sayaa.todolistjetpack.data.TodoRepository
import com.sayaa.todolistjetpack.util.Routes
import com.sayaa.todolistjetpack.util.Routes.ADD_EDIT_TODO
import com.sayaa.todolistjetpack.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
):ViewModel() {

    val todos = repository.getAllTodo()
    private var deletedTodo: Todo? = null

    private val _uiEvents = Channel<UiEvents>()
    var uiEvents = _uiEvents.receiveAsFlow()


    fun onEvent(event: TodolistEvent){
        when(event){
            is TodolistEvent.onAddTodoClick->{
                sendUiEvent(UiEvents.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodolistEvent.onUndoDeleteClick->{
                deletedTodo?.let { todo->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
            is TodolistEvent.onDeleteTodo->{
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTodo(event.todo)
                }
                  sendUiEvent(UiEvents.ShowSnackbar(
                      "Todo Deleted",
                      "undo"
                  ))
            }
            is TodolistEvent.onTodoclick->{
                sendUiEvent(UiEvents.Navigate( Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }
            is TodolistEvent.onDoneChangeClick->{
                 viewModelScope.launch {
                     repository.insertTodo(event.todo.
                     copy(isDone = event.isDone))
                 }
            }
        }
    }
    fun sendUiEvent(event: UiEvents){
        viewModelScope.launch {
            _uiEvents.send(event)
        }
    }
}