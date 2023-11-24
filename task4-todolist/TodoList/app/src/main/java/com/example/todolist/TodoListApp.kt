package com.example.todolist

import android.app.Application
import com.example.todolist.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TodoListApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TodoListApp)
            modules(appModule)
        }
    }
}