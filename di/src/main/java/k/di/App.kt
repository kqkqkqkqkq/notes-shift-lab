package k.di

import android.app.Application
import k.di.modules.appModule
import k.di.modules.dataStoreModule
import k.di.modules.databaseModule
import k.di.modules.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    val modules = listOf(
        appModule,
        databaseModule,
        repositoryModule,
        dataStoreModule,
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }
}