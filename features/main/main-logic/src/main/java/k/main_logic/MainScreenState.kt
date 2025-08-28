package k.main_logic

sealed interface MainScreenState {
    data object Initial : MainScreenState
    data object Loading : MainScreenState
    data class Failure(val message: String?) : MainScreenState
    data class Content(val content: MainContentState) : MainScreenState
}