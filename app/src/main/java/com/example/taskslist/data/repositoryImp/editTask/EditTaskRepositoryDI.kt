package com.example.todoapp.data.repositoryImp.editTask

import com.example.taskslist.data.database.TaskDao
import com.example.taskslist.data.repositoryImp.editTask.EditTaskRepositoryImp
import com.example.taskslist.repository.EditTaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class EditTaskRepositoryDI {
    @Provides
    fun provideEditTaskRepository(taskDao: TaskDao): EditTaskRepository {
        return EditTaskRepositoryImp(taskDao)
    }
}