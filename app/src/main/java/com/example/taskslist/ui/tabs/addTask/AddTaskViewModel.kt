package com.example.taskslist.ui.tabs.addTask

import androidx.lifecycle.ViewModel
import com.example.taskslist.data.database.Task
import com.example.taskslist.data.repositoryImp.addTask.AddTaskRepositoryImp
import com.example.taskslist.data.repositoryImp.editTask.EditTaskRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskRepositoryImp: AddTaskRepositoryImp,
    private val editTaskRepositoryImp: EditTaskRepositoryImp
):ViewModel() {

    fun addTask(task: Task){
        addTaskRepositoryImp.addTask(task)
    }
    fun editTask(task: Task){
        editTaskRepositoryImp.editTask(task)
    }
}