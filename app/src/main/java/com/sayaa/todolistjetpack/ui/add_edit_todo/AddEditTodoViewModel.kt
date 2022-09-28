package com.sayaa.todolistjetpack.ui.add_edit_todo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sayaa.todolistjetpack.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    private val savedStateHandle: SavedStateHandle
 ) :ViewModel(){
     
}