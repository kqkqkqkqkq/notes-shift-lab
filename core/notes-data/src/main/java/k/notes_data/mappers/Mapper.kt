package k.notes_data.mappers

import k.notes_data.models.Note
import k.notes_data.models.Priority
import k.notes_database.models.NoteDbo
import k.notes_database.models.PriorityDbo

fun NoteDbo.toNote(): Note = Note(
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

fun Note.toNoteDbo(): NoteDbo = NoteDbo(
    id = this.id,
    title = this.title,
    text = this.text,
    isComplete = this.isComplete,
    completedAt = this.completedAt,
    priority = this.priority?.toPriorityDbo(),
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    dueAt = this.dueAt,
    reminderAt = this.reminderAt,
    pinned = this.pinned,
    archived = this.archived,
    trashed = this.trashed,
    colorHex = this.colorHex,
)

internal fun PriorityDbo.toPriority(): Priority = when (this) {
    PriorityDbo.LOW -> Priority.LOW
    PriorityDbo.MEDIUM -> Priority.MEDIUM
    PriorityDbo.HIGH -> Priority.HIGH
}

internal fun Priority.toPriorityDbo(): PriorityDbo = when (this) {
    Priority.LOW -> PriorityDbo.LOW
    Priority.MEDIUM -> PriorityDbo.MEDIUM
    Priority.HIGH -> PriorityDbo.HIGH
}