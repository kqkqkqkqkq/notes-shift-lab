package k.ui_models.mapper

import k.notes_data.models.Note
import k.notes_data.models.Priority
import k.ui_models.models.NoteUio
import k.ui_models.models.PriorityUio


fun Note.toNoteUio(): NoteUio = NoteUio(
    id = this.id,
    title = this.title,
    text = this.text,
    isComplete = this.isComplete,
    completedAt = this.completedAt,
    priority = this.priority?.toPriorityUio(),
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    dueAt = this.dueAt,
    reminderAt = this.reminderAt,
    pinned = this.pinned,
    archived = this.archived,
    trashed = this.trashed,
    colorHex = this.colorHex,
)

fun NoteUio.toNote(): Note = Note(
    id = this.id,
    title = this.title,
    text = this.text,
    isComplete = this.isComplete,
    completedAt = this.completedAt,
    priority = this.priority?.toPriority(),
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    dueAt = this.dueAt,
    reminderAt = this.reminderAt,
    pinned = this.pinned,
    archived = this.archived,
    trashed = this.trashed,
    colorHex = this.colorHex,
)

internal fun PriorityUio.toPriority(): Priority = when (this) {
    PriorityUio.LOW -> Priority.LOW
    PriorityUio.MEDIUM -> Priority.MEDIUM
    PriorityUio.HIGH -> Priority.HIGH
}

internal fun Priority.toPriorityUio(): PriorityUio = when (this) {
    Priority.LOW -> PriorityUio.LOW
    Priority.MEDIUM -> PriorityUio.MEDIUM
    Priority.HIGH -> PriorityUio.HIGH
}