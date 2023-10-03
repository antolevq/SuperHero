package it.leva.superhero

import android.app.Application
import it.leva.di.apiServicesModule
import it.leva.di.databaseModule
import it.leva.di.repositoriesModule
import it.leva.di.useCasesModule
import it.leva.superhero.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HeroApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HeroApplication)
            modules(
                databaseModule,
                apiServicesModule,
                repositoriesModule,
                useCasesModule,
                viewModelsModule
            )
        }
    }
}