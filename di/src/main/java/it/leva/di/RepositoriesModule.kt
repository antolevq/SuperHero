package it.leva.di

import it.leva.data.repository.HeroRepositoryImpl
import it.leva.data.repository.PreferenceRepositoryImpl
import it.leva.domain.repository.HeroRepository
import it.leva.domain.repository.PreferenceRepository
import org.koin.dsl.module

val repositoriesModule = module {
    factory<HeroRepository> { HeroRepositoryImpl(heroService = get(), dataSource = get()) }
    factory<PreferenceRepository> { PreferenceRepositoryImpl(dataSource = get()) }
}