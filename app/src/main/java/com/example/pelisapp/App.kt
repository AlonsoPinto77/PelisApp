package com.example.pelisapp

import android.app.Application
import com.example.pelisapp.di.appModule
import com.example.pelisapp.di.coreModules
import com.example.pelisapp.di.dataModules
import com.example.pelisapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(coreModules, dataModules, presentationModule, appModule )
            androidLogger()
        }
    }
}