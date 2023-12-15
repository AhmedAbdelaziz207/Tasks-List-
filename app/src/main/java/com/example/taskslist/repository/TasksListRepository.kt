package com.example.taskslist.repository

import androidx.lifecycle.LiveData
import com.example.taskslist.data.database.Task

interface TasksListRepository {

    fun getAllTasks():List<Task>
//
    fun getNotDoneTasks():LiveData<List<Task>>
//
     fun getDoneTasks():LiveData<List<Task>>

    fun deleteTask(task: Task)

    fun changeTaskState(task: Task)
}