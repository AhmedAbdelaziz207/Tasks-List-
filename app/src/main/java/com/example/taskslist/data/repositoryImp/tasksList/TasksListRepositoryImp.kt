package com.example.taskslist.data.repositoryImp.tasksList

import androidx.lifecycle.LiveData
import com.example.taskslist.data.database.Task
import com.example.taskslist.data.database.TaskDao
import com.example.taskslist.repository.TasksListRepository
import javax.inject.Inject

class TasksListRepositoryImp @Inject constructor(private val tasksDao: TaskDao):
    TasksListRepository {
    override fun getAllTasks(): List<Task> {
     return   tasksDao.getAllTasks()
    }

    override fun getNotDoneTasks():LiveData < List<Task>> {
    return tasksDao.getNotDoneTasks()
    }

    override fun getDoneTasks(): LiveData<List<Task>> {
       return tasksDao.getAllDoneTasks()
    }
//
//    override fun getAllTasksLiveByDate(date: Long): LiveData<List<Task>> {
//        return tasksDao.getTasksByDate(date)
//    }

    override fun deleteTask(task: Task) {
       tasksDao.deleteTask(task)
    }

    override fun changeTaskState(task: Task) {
        task.isDone = true
        tasksDao.updateTask(task)
    }
}