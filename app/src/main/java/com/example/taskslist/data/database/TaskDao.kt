package com.example.taskslist.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert
    fun addTask(task: Task)
    @Update
    fun updateTask(task: Task)
    @Delete
    fun deleteTask(task: Task)
    @Query("select * from TasksDb where isDone = 1")
    fun getAllDoneTasks():LiveData<List<Task>>

    @Query("select * from TasksDb")
    fun getAllTasks():List<Task>

    @Query("delete from TasksDb ")
    fun deleteAllTasks()
    @Query("select * from TasksDb where isDone = 0")
    fun getNotDoneTasks(): LiveData<List<Task>>
}