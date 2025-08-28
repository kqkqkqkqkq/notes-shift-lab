package k.di.modules

import k.notes_database.NotesDatabase
import k.notes_database.createDatabase
import org.koin.dsl.module

val databaseModule = module {
    single<NotesDatabase> {
        createDatabase(context = get())
    }
}