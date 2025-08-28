package k.di.modules

import k.notes_data.INotesRepository
import k.notes_data.NotesRepository
import k.settings.ISettingsRepository
import k.settings.SettingsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ISettingsRepository> {
        SettingsRepository(
            dataStore = get(),
        )
    }
    single<INotesRepository> {
        NotesRepository(
            database = get(),
        )
    }
}