package k.notes_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import k.notes_database.constants.Constants.DATABASE_NAME
import k.notes_database.models.NoteDbo
import k.notes_database.utils.Converters

class NotesDatabase internal constructor(private val database: NotesRoomDatabase) {
    val dao: NotesDao
        get() = database.notesDao()
}

@Database(entities = [NoteDbo::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
internal abstract class NotesRoomDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}

fun createDatabase(context: Context): NotesDatabase {
    val notesDatabase = Room.databaseBuilder(
        checkNotNull(context.applicationContext),
        NotesRoomDatabase::class.java,
        DATABASE_NAME,
    ).build()
    return NotesDatabase(notesDatabase)
}