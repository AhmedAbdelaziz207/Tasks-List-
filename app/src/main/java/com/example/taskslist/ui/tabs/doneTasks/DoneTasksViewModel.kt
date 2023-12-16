package com.example.taskslist.ui.tabs.doneTasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.taskslist.data.database.Task
import com.example.taskslist.data.repositoryImp.editTask.EditTaskRepositoryImp
import com.example.taskslist.data.repositoryImp.tasksList.TasksListRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class DoneTasksViewModel @Inject constructor(
    private val tasksListRepositoryImp: TasksListRepositoryImp,
    private val editTaskRepositoryImp: EditTaskRepositoryImp
):ViewModel() {

    fun getDoneTasks():LiveData<List<Task>>{
        return tasksListRepositoryImp.getDoneTasks()
    }
    fun updateTask(task: Task){
        editTaskRepositoryImp.editTask(task)
    }
    fun deleteTask(task: Task){
        tasksListRepositoryImp.deleteTask(task)
    }
}