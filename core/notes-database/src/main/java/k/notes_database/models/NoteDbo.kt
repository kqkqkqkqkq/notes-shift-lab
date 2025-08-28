package k.notes_database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import k.notes_database.constants.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class NoteDbo(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "text") val text: String? = null,
    @ColumnInfo(name = "is_complete") val isComplete: Boolean = false,
    @ColumnInfo(name = "completed_at") val completedAt: Long? = null,
    @ColumnInfo(name = "priority") val priority: PriorityDbo? = null,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "updated_at") val updatedAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "due_at") val dueAt: Long? = null,
    @ColumnInfo(name = "reminder_at") val reminderAt: Long? = null,
    @ColumnInfo(name = "pinned") val pinned: Boolean = false,
    @ColumnInfo(name = "archived") val archived: Boolean = false,
    @ColumnInfo(name = "trashed") val trashed: Boolean = false,
    @ColumnInfo(name = "color_hex") val colorHex: String? = null,
)