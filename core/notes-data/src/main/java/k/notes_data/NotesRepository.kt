package k.notes_data

import k.notes_data.mappers.toNote
import k.notes_data.mappers.toNoteDbo
import k.notes_data.mappers.toPriorityDbo
import k.notes_data.models.Note
import k.notes_data.models.Priority
import k.notes_database.NotesDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepository(
    private val database: NotesDatabase,
) : INotesRepository {
    override suspend fun insert(note: Note): Long =
        database.dao.insert(note.toNoteDbo())


    override suspend fun insertAll(notes: List<Note>) {
        database.dao.insertAll(notes.map { it.toNoteDbo() })
    }

    override suspend fun delete(note: Note) {
        database.dao.delete(note.toNoteDbo())
    }

    override suspend fun deleteAll(notes: List<Note>) {
        database.dao.deleteAll(notes.map { it.toNoteDbo() })
    }

    override suspend fun deleteAllTrashed() {
        database.dao.deleteAllTrashed()
    }

    override suspend fun deleteById(id: Long) {
        database.dao.deleteById(id)
    }

    override suspend fun getAll(): Flow<List<Note>> = database.dao.getAll().map { notes ->
        notes.map { it.toNote() }
    }

    override suspend fun getById(id: Long): Note? = database.dao.getById(id)?.toNote()

    override suspend fun search(query: String): Flow<List<Note>> =
        database.dao.search(query).map { notes ->
            notes.map { it.toNote() }
        }

    override suspend fun setComplete(id: Long, isComplete: Boolean) {
        database.dao.setComplete(id, isComplete)
    }

    override suspend fun setPinned(id: Long, pinned: Boolean) {
        database.dao.setPinned(id, pinned)
    }

    override suspend fun setArchived(id: Long, archived: Boolean) {
        database.dao.setArchived(id, archived)
    }

    override suspend fun setTrashed(id: Long, trashed: Boolean) {
        database.dao.setTrashed(id, trashed)
    }

    override suspend fun setPriority(id: Long, priority: Priority) {
        database.dao.setPriority(id, priority.toPriorityDbo())
    }

    override suspend fun setDueAt(id: Long, dueAt: Long) {
        database.dao.setDueAt(id, dueAt)
    }

    override suspend fun setReminderAt(id: Long, reminderAt: Long) {
        database.dao.setReminderAt(id, reminderAt)
    }

    override suspend fun setTitle(id: Long, title: String) {
        database.dao.setTitle(id, title)
    }

    override suspend fun setText(id: Long, text: String) {
        database.dao.setText(id, text)
    }

}