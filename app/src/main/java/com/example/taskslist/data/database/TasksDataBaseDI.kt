package com.example.taskslist.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TasksDataBaseDI {
    @Singleton
    @Provides
    fun provideTasksDataBase(@ApplicationContext context:Context ):TasksDataBase{
        return Room
            .databaseBuilder(context, TasksDataBase::class.java,"TasksDb")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideTaskDao(tasksDataBase: TasksDataBase):TaskDao{
        return tasksDataBase.tasksDao()
    }
}