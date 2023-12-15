package com.example.taskslist.data.repositoryImp.editTask

import com.example.taskslist.data.database.Task
import com.example.taskslist.data.database.TaskDao

import com.example.taskslist.repository.EditTaskRepository
import javax.inject.Inject

class EditTaskRepositoryImp @Inject constructor(private val taskDao: TaskDao): EditTaskRepository {
    override fun editTask(task: Task) {
        taskDao.updateTask(task)
    }

}