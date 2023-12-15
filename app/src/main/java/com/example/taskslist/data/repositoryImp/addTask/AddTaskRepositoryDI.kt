package com.example.taskslist.data.repositoryImp.addTask

import com.example.taskslist.data.database.TaskDao
import com.example.taskslist.repository.AddTaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AddTaskRepositoryDI {
    @Provides
    fun provideAddTaskRepository(taskDao: TaskDao): AddTaskRepository {
        return AddTaskRepositoryImp(taskDao)
    }
}