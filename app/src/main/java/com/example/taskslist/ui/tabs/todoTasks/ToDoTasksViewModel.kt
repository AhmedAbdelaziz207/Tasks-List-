package com.example.taskslist.ui.tabs.todoTasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskslist.data.database.Task
import com.example.taskslist.data.repositoryImp.addTask.AddTaskRepositoryImp
import com.example.taskslist.data.repositoryImp.editTask.EditTaskRepositoryImp
import com.example.taskslist.data.repositoryImp.tasksList.TasksListRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class ToDoTasksViewModel @Inject constructor(
    private val tasksListRepositoryImp: TasksListRepositoryImp,
    private val addTaskRepositoryImp: AddTaskRepositoryImp
    , private val editTaskRepositoryImp: EditTaskRepositoryImp
):ViewModel() {
    var taskLiveData = MutableLiveData<List<Task>>()

    fun getAllTaskLive():LiveData<List<Task>>{

       return tasksListRepositoryImp.getNotDoneTasks()
    }

    fun addTask(task: Task){
        addTaskRepositoryImp.addTask(task)
    }
    fun deleteTask(task: Task){
        tasksListRepositoryImp.deleteTask(task)
    }
    fun updateTask(task: Task){
        editTaskRepositoryImp.editTask(task)
    }


}