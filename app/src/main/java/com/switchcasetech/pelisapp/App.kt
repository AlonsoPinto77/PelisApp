package com.switchcasetech.pelisapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@App)
            //modules(coreModules, dataModules, presentationModule, appModule )
            androidLogger()
        }
    }
}