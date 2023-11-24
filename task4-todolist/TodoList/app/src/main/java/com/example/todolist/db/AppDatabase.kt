package com.example.todolist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDate

@TypeConverters(MyTypeConverter::class)
@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

}

suspend fun populateDatabase(taskDao: TaskDao) {
    val predefinedTask = listOf(
        Task(1, "Task1", "Description1", LocalDate.now(), false),
        Task(2, "Task2", "Longer Description2", LocalDate.of(2023, 12, 31), true),
        Task(3, "Task3", "Different Description3. A little longer one.", LocalDate.of(2023, 11, 2), false),
        Task(4, "Task4", "", LocalDate.of(2023, 4, 14), true),
        Task(5, "Task5", "", LocalDate.of(2023, 3, 10), false),
        Task(6, "Task6", "Description6", LocalDate.of(2023, 1, 7), true),
        Task(7, "Task7", "Description7", LocalDate.of(2023, 10, 3), false),
        Task(8, "Task8", "Description8", LocalDate.of(2023, 9, 30), false),
        Task(9, "Task9", "Description9", LocalDate.of(2023, 3, 1), true),
        Task(10, "Task10", "Description10", LocalDate.of(2023, 12, 14), false),
    )
    predefinedTask.forEach { taskDao.insert(it) }
}