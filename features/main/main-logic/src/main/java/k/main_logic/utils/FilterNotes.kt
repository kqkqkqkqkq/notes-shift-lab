package k.main_logic.utils

import k.ui_models.models.NoteUio
import k.ui_models.models.PriorityUio

fun filterNotes(
    notes: List<NoteUio>,
    selectedFilters: Set<Filter>
): List<NoteUio> {
    if (selectedFilters.isEmpty()) return notes.filter { !it.trashed }

    return notes.filter { note ->
        selectedFilters.all { filter ->
            when (filter) {
                Filter.TRASHED -> note.trashed
                Filter.COMPLETED -> note.isComplete
                Filter.ACTIVE -> !note.isComplete && !note.trashed
                Filter.PRIORITY_HIGH -> note.priority == PriorityUio.HIGH
                Filter.PRIORITY_MEDIUM -> note.priority == PriorityUio.MEDIUM
                Filter.PRIORITY_LOW -> note.priority == PriorityUio.LOW
            }
        }
    }
}