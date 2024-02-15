package com.example.domain.di

import com.example.domain.interactors.movies.GetMovies
import com.example.domain.interactors.movies.GetMoviesImpl
import com.example.domain.interactors.movies.GetMoviesOffline
import com.example.domain.interactors.movies.GetMoviesOfflineImpl
import com.example.domain.interactors.user.SaveUser
import com.example.domain.interactors.user.SaveUserImpl
import com.example.domain.interactors.user.UserSignIn
import com.example.domain.interactors.user.UserSignInImpl
import org.koin.dsl.module

val interactionModule = module {
    factory<UserSignIn> {UserSignInImpl(get())}
    factory<SaveUser>{SaveUserImpl(get())}
    factory<GetMovies> {GetMoviesImpl(get())}
    factory<GetMoviesOffline>{GetMoviesOfflineImpl(get())}
}