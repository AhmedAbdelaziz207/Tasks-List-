package com.example.taskslist.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "TasksDb")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    @ColumnInfo
    var taskContent : String? = null ,
    @ColumnInfo
    var isDone : Boolean = false ,
    @ColumnInfo
    val dateTime: Long? = null
)
