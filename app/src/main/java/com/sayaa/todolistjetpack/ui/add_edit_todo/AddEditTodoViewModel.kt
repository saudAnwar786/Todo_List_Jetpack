package com.sayaa.todolistjetpack.ui.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayaa.todolistjetpack.data.Todo
import com.sayaa.todolistjetpack.data.TodoRepository
import com.sayaa.todolistjetpack.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
     savedStateHandle: SavedStateHandle
 ) :ViewModel(){


     var title by mutableStateOf("")
     private set

     var description by mutableStateOf("")
    private set

    var todo by mutableStateOf<Todo?>(null)
    private set

    private val _uiEvents = Channel<UiEvents>()
    var uiEvents = _uiEvents.receiveAsFlow()

    init {

        var todoId = savedStateHandle.get<Int>("todoId")
        if(todoId != -1){
            viewModelScope.launch {
                repository.getTodoById(todoId!!).let { todo->
                    title = todo.title
                    description = todo.descrition?: ""
                    this@AddEditTodoViewModel.todo= todo


                }
            }

        }


    }
    fun onEvent(event: AddEditTodoEvent){

        when(event){
            is AddEditTodoEvent.onDescriptionChanged->{
                description = event.description
            }
            is AddEditTodoEvent.onTitleChanged->{
                title = event.title
            }
            is AddEditTodoEvent.onSaveTodoClick->{
                viewModelScope.launch {
                    if(title.isBlank()) {
                        sendUiEvent(
                            UiEvents.ShowSnackbar(
                                message = "Title Can't be Empty",

                                )


                        )
                        return@launch
                    }else{

                            repository.insertTodo(
                                Todo(
                                    title = title,
                                    descrition = description,
                                    isDone = todo?.isDone?:false,
                                    id = todo?.id
                                )
                            )
                        sendUiEvent(UiEvents.PopBackStack)

                    }
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