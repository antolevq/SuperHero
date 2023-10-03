package it.leva.di

import it.leva.domain.usecase.HeroUseCase
import it.leva.domain.usecase.HeroUseCaseImpl
import it.leva.domain.usecase.PreferenceUseCase
import it.leva.domain.usecase.PreferenceUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
    factory<HeroUseCase> { HeroUseCaseImpl(heroRepository = get()) }
    factory<PreferenceUseCase> { PreferenceUseCaseImpl(preferenceRepository = get()) }
}