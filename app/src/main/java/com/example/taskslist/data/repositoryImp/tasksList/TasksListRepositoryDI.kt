package com.example.taskslist.data.repositoryImp.tasksList

import com.example.taskslist.data.database.TaskDao
import com.example.taskslist.repository.TasksListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class TasksListRepositoryDI {
    @Provides
    fun provideTasksListRepository(tasksDao: TaskDao): TasksListRepository {
        return TasksListRepositoryImp(tasksDao)
    }
}