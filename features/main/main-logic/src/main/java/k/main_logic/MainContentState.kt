package k.main_logic

import k.main_logic.utils.Filter
import k.notes_data.models.Note
import kotlinx.coroutines.flow.Flow

data class MainContentState(
    val notes: List<Note>,
    val selectedFilters: List<Filter>,
) {
    companion object {
        fun default() = MainContentState(
            notes = emptyList(),
            selectedFilters = emptyList(),
        )
    }
}