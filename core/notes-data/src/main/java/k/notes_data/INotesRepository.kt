package k.notes_data

import k.notes_data.models.Note
import k.notes_data.models.Priority
import kotlinx.coroutines.flow.Flow

interface INotesRepository {
    suspend fun insert(note: Note): Long
    suspend fun insertAll(notes: List<Note>)
    suspend fun delete(note: Note)
    suspend fun deleteAll(notes: List<Note>)
    suspend fun deleteAllTrashed()
    suspend fun deleteById(id: Long)
    suspend fun getAll(): Flow<List<Note>>
    suspend fun getById(id: Long): Note?
    suspend fun search(query: String): Flow<List<Note>>
    suspend fun setComplete(id: Long, isComplete: Boolean)
    suspend fun setPinned(id: Long, pinned: Boolean)
    suspend fun setArchived(id: Long, archived: Boolean)
    suspend fun setTrashed(id: Long, trashed: Boolean)
    suspend fun setPriority(id: Long, priority: Priority)
    suspend fun setDueAt(id: Long, dueAt: Long)
    suspend fun setReminderAt(id: Long, reminderAt: Long)
    suspend fun setTitle(id: Long, title: String)
    suspend fun setText(id: Long, text: String)
}