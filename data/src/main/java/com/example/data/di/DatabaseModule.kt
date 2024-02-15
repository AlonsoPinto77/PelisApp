package com.example.data.di

import androidx.room.Room
import com.example.data.common.DB_NAME
import com.example.data.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module{
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration().build()
    }

    single{ get<AppDatabase>().movieDao()}
    single{ get<AppDatabase>().userDao()}
}