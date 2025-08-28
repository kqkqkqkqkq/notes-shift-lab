package k.di.modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import k.settings.createDataStore
import org.koin.dsl.module

val dataStoreModule = module {
    single<DataStore<Preferences>> {
        createDataStore(context = get())
    }
}