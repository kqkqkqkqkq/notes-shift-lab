package k.main_logic

sealed interface MainAction {
    data object CreateNote: MainAction
    data object OpenSettings: MainAction
    data class PinNote(val id: Long, val isPinned: Boolean): MainAction
    data class CompletedChange(val id: Long, val isCompleted: Boolean): MainAction
}