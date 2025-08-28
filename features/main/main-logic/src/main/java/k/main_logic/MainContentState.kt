package k.main_logic

import k.main_logic.utils.Filter
import k.ui_models.models.NoteUio

data class MainContentState(
    val notes: List<NoteUio>,
    val selectedFilters: List<Filter>,
    val isSettingsOpen: Boolean = false,
) {
    companion object {
        fun default() = MainContentState(
            notes = emptyList(),
            selectedFilters = emptyList(),
        )
    }
}