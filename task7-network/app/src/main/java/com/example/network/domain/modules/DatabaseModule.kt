package com.example.network.domain.modules

import androidx.room.Room
import com.example.network.db.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().categoryDao() }
    single { get<AppDatabase>().productDao() }
}