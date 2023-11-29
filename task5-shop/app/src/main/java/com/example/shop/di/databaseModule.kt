package com.example.shop.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.shop.database.ShopDatabase
import com.example.shop.database.populateDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), ShopDatabase::class.java, "shop_db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db : SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val appDatabase = get<ShopDatabase>()
                    val productDao = appDatabase.productDao()
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(productDao)
                    }
                }
            })
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<ShopDatabase>().productDao() }

}