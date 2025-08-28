package k.main_logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import k.notes_data.INotesRepository
import k.ui_models.mapper.toNote
import k.ui_models.mapper.toNoteUio
import k.ui_models.models.NoteUio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: INotesRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<MainScreenState>(MainScreenState.Initial)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = MainScreenState.Loading
            try {
                repository.getAll()
                    .map { notesList ->
                        MainContentState(
                            notes = notesList.map { it.toNoteUio() },
                            selectedFilters = emptyList(),
                        )
                    }
                    .collect { newState ->
                        _state.value = MainScreenState.Content(content = newState)
                    }
            } catch (e: Exception) {
                _state.value = MainScreenState.Failure(e.message)
            }
        }
    }

    fun onAction(action: MainAction) {
        when (action) {
            is MainAction.CreateNote -> createNote()
            is MainAction.OpenSettings -> TODO()
            is MainAction.PinNote -> pinNote(action.id, action.isPinned)
            is MainAction.CompletedChange -> completeChange(action.id, action.isCompleted)
        }
    }

    private fun createNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val emptyNoteToInsert = NoteUio.default()
            val id = repository.insert(emptyNoteToInsert.toNote())
        }
    }

    private fun completeChange(id: Long, isCompleted: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setComplete(id, isCompleted)
        }
    }

    private fun pinNote(id: Long, pinned: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setPinned(id, pinned)
        }
    }
}