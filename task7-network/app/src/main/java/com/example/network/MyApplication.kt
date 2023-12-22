package com.example.network

import android.app.Application
import com.example.network.domain.modules.restModule
import com.example.network.domain.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(restModule, viewModelModule)
        }

    }

}