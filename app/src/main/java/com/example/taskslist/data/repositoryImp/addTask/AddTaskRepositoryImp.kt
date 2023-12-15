package com.example.taskslist.data.repositoryImp.addTask

import com.example.taskslist.data.database.Task
import com.example.taskslist.data.database.TaskDao
import com.example.taskslist.repository.AddTaskRepository
import javax.inject.Inject

class AddTaskRepositoryImp @Inject constructor(private val taskDao: TaskDao): AddTaskRepository {
    override fun addTask(task: Task) {
        taskDao.addTask(task)
    }
}