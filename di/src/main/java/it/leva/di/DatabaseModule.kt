package it.leva.di

import it.leva.data.persistence.HeroDb
import it.leva.data.persistence.dataSource.PreferenceDataSource
import it.leva.data.persistence.dataSource.PreferenceDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { HeroDb.instance(context = androidContext()) }
    factory<PreferenceDataSource> { PreferenceDataSourceImpl(heroDb = get()) }
}