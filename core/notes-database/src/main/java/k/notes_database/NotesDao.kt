package k.notes_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import k.notes_database.constants.Constants.TABLE_NAME
import k.notes_database.models.NoteDbo
import k.notes_database.models.PriorityDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteDbo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<NoteDbo>)

    @Delete
    suspend fun delete(note: NoteDbo)

    @Delete
    suspend fun deleteAll(notes: List<NoteDbo>)

    @Query("DELETE FROM $TABLE_NAME WHERE trashed = 1")
    suspend fun deleteAllTrashed()

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): NoteDbo?

    @Query("SELECT * FROM $TABLE_NAME ORDER BY pinned DESC, updated_at DESC")
    fun getAll(): Flow<List<NoteDbo>>

    @Query(
        """
        SELECT * FROM $TABLE_NAME
        WHERE trashed = 0 AND (title LIKE :query OR text LIKE :query)
        ORDER BY pinned DESC, updated_at DESC
    """
    )
    fun search(query: String): Flow<List<NoteDbo>>

    @Query("UPDATE $TABLE_NAME SET is_complete = :isComplete, completed_at = :completedAt WHERE id = :id")
    suspend fun setComplete(
        id: Long,
        isComplete: Boolean,
        completedAt: Long = System.currentTimeMillis(),
    )

    @Query("UPDATE $TABLE_NAME SET pinned = :pinned WHERE id = :id")
    suspend fun setPinned(id: Long, pinned: Boolean)

    @Query("UPDATE $TABLE_NAME SET archived = :archived WHERE id = :id")
    suspend fun setArchived(id: Long, archived: Boolean)

    @Query("UPDATE $TABLE_NAME SET trashed = :trashed WHERE id = :id")
    suspend fun setTrashed(id: Long, trashed: Boolean)

    @Query("UPDATE $TABLE_NAME SET priority = :priority WHERE id = :id")
    suspend fun setPriority(id: Long, priority: PriorityDbo)

    @Query("UPDATE $TABLE_NAME SET due_at = :dueAt WHERE id = :id")
    suspend fun setDueAt(id: Long, dueAt: Long)

    @Query("UPDATE $TABLE_NAME SET reminder_at = :reminderAt WHERE id = :id")
    suspend fun setReminderAt(id: Long, reminderAt: Long)

    @Query("UPDATE $TABLE_NAME SET title = :title, updated_at = :updatedAt WHERE id = :id")
    suspend fun setTitle(id: Long, title: String, updatedAt: Long = System.currentTimeMillis())

    @Query("UPDATE $TABLE_NAME SET text = :text, updated_at = :updatedAt WHERE id = :id")
    suspend fun setText(id: Long, text: String, updatedAt: Long = System.currentTimeMillis())
}