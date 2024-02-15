package com.example.pelisapp.di

import com.example.pelisapp.utils.SharedUtils
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module{
    single {SharedUtils(get(), androidContext())}
}