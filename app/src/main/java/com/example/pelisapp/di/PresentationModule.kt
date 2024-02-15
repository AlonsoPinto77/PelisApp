package com.example.pelisapp.di

import com.example.data.di.databaseModule
import com.example.data.di.networkModule
import com.example.data.di.repositoryModule
import com.example.domain.di.interactionModule
import com.example.pelisapp.ui.catalogue.CatalogueViewModel
import com.example.pelisapp.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coreModules = module {
    includes(interactionModule)
}

val dataModules = module {
    includes(networkModule, repositoryModule, databaseModule)
}

val presentationModule = module{
    viewModel { LoginViewModel(get(), get()) }
    viewModel { CatalogueViewModel(get(), get()) }
}