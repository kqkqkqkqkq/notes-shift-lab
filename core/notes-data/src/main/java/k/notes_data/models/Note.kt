package k.notes_data.models

data class Note(
    val id: Long,
    val title: String?,
    val text: String?,
    val isComplete: Boolean,
    val completedAt: Long?,
    val priority: Priority?,
    val createdAt: Long,
    val updatedAt: Long,
    val dueAt: Long?,
    val reminderAt: Long?,
    val pinned: Boolean,
    val archived: Boolean,
    val trashed: Boolean,
    val colorHex: String?,
)