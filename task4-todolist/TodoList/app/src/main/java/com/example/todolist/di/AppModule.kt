package com.example.todolist.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todolist.TaskViewModel
import com.example.todolist.db.AppDatabase
import com.example.todolist.db.populateDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { TaskViewModel(get()) }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // pre-populate data
                    val appDatabase = get<AppDatabase>()
                    val taskDao = appDatabase.taskDao()
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(taskDao)
                    }
                }
            })
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().taskDao() }

}