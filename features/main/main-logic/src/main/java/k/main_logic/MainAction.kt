package k.main_logic

import k.notes_data.models.Note

sealed interface MainAction {
    data object CreateNote: MainAction
    data object OpenSettings: MainAction
    data class OpenDetail(val note: Note): MainAction
    data class UpdateComplete(val id: Long): MainAction
}