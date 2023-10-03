package it.leva.di

import it.leva.data.network.RetrofitClient
import org.koin.dsl.module

val apiServicesModule = module {
    single { RetrofitClient.heroService }
}