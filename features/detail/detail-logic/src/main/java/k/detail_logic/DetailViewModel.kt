package k.detail_logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import k.notes_data.INotesRepository
import k.ui_models.mapper.toNoteUio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: INotesRepository,
    private val id: Long,
) : ViewModel() {
    private val _state = MutableStateFlow<DetailScreenState>(DetailScreenState.Initial)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = DetailScreenState.Loading
            try {
                val note = repository.getById(id)?.toNoteUio()
                if (note != null) {
                    _state.value = DetailScreenState.Content(
                        content = DetailContentState(note = note)
                    )
                } else {
                    _state.value = DetailScreenState.Failure("Note not found")
                }
            } catch (e: Exception) {
                _state.value = DetailScreenState.Failure(e.message ?: "Unknown error")
            }
        }
    }

    fun onAction(action: DetailAction) {
        when (action) {
            is DetailAction.MoveToTrash -> moveToTrash()
            is DetailAction.UpdateStatus -> TODO()
            is DetailAction.UpdateText -> updateText(action.text)
            is DetailAction.UpdateTitle -> updateTitle(action.title)
        }
    }

    private fun moveToTrash() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setTrashed(id, true)
        }
    }

    private fun updateTitle(newTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setTitle(id, newTitle)
        }
    }

    private fun updateText(newText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setText(id, newText)
        }
    }
}