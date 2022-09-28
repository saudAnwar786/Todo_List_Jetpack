package com.sayaa.todolistjetpack.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Todo(
    val title : String ,
    val descrition : String?,
    val isDone : Boolean,
    @PrimaryKey(autoGenerate = true) val id : Int?
)
