package com.example.data.di

import com.example.data.repositories.movie.MovieRepositoryImpl
import com.example.data.repositories.user.UserRepositoryImpl
import com.example.data.utils.LocalStorage
import com.example.data.utils.NetworkUtils
import com.example.data.utils.NetworkUtilsImpl
import com.example.domain.interactors.movies.MoviesRepository
import com.example.domain.interactors.user.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalStorage(androidContext()) }

    factory<NetworkUtils> { NetworkUtilsImpl(androidContext()) }
    factory<UserRepository>{ UserRepositoryImpl(get()) }
    factory<MoviesRepository>{ MovieRepositoryImpl(get(),get())  }
}