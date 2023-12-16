package com.example.taskslist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Task::class] , exportSchema = true , version = 1)
abstract class TasksDataBase : RoomDatabase() {
    abstract fun tasksDao(): TaskDao
}