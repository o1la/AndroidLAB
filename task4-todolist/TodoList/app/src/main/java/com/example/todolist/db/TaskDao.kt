package com.example.todolist.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun selectAll(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task): Long

    @Query("DELETE FROM task_table WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()

    @Query("UPDATE task_table SET status = :status WHERE id = :id")
    suspend fun updateTask(status: Boolean, id: Long)
}