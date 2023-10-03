package it.leva.superhero.di

import it.leva.superhero.ui.heroList.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { MainViewModel(heroUseCase = get(), preferenceUseCase = get()) }
}