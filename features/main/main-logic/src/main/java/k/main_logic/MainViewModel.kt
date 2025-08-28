package k.main_logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import k.notes_data.INotesRepository
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
                            notes = notesList,
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
}