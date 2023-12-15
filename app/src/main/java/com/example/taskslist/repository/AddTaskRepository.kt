package com.example.taskslist.repository

import com.example.taskslist.data.database.Task

interface AddTaskRepository {
    fun addTask(task: Task)
}