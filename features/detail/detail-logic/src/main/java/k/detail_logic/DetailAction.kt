package k.detail_logic

sealed interface DetailAction {
    data class UpdateStatus(val status: Boolean): DetailAction
    data class UpdateText(val text: String): DetailAction
    data class UpdateTitle(val title: String): DetailAction
    data object MoveToTrash : DetailAction
}