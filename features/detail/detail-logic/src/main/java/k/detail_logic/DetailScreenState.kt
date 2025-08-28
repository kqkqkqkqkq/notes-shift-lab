package k.detail_logic

sealed interface DetailScreenState {
    data object Initial : DetailScreenState
    data object Loading : DetailScreenState
    data class Failure(val message: String?) : DetailScreenState
    data class Content(val content: DetailContentState) : DetailScreenState
}