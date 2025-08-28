package k.ui_models.models

data class NoteUio(
    val id: Long,
    val title: String?,
    val text: String?,
    val isComplete: Boolean,
    val completedAt: Long?,
    val priority: PriorityUio?,
    val createdAt: Long,
    val updatedAt: Long,
    val dueAt: Long?,
    val reminderAt: Long?,
    val pinned: Boolean,
    val archived: Boolean,
    val trashed: Boolean,
    val colorHex: String?,
) {
    companion object {
        fun default(): NoteUio {
            return NoteUio(
                id = 0L,
                title = "",
                text = "",
                isComplete = false,
                completedAt = null,
                priority = null,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis(),
                dueAt = null,
                reminderAt = null,
                pinned = false,
                archived = false,
                trashed = false,
                colorHex = null,
            )
        }
    }
}