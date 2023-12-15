package com.example.taskslist.repository

import com.example.taskslist.data.database.Task

interface EditTaskRepository {
    fun editTask(task: Task)
}