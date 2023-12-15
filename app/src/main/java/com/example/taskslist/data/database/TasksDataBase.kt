package com.example.taskslist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Task::class] , exportSchema = true , version = 1)
abstract class TasksDataBase : RoomDatabase() {
    abstract fun tasksDao(): TaskDao
}