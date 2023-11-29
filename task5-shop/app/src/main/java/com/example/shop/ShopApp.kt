package com.example.shop

import android.app.Application
import com.example.shop.di.databaseModule
import com.example.shop.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShopApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShopApp)
            modules(databaseModule, viewModelModule)
        }
    }
}