package k.main_ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import k.main_logic.MainScreenState
import k.main_logic.MainViewModel
import k.main_ui.state.MainScreenContent
import k.main_ui.state.MainScreenFailure
import k.main_ui.state.MainScreenLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit,
) {
    MainScreenUI(
        isDarkTheme,
        onThemeToggle,
    )
}

@Composable
fun MainScreenUI(
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit,
    viewModel: MainViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    when(val currentState = state) {
        is MainScreenState.Content -> MainScreenContent(
            isDarkTheme,
            onThemeToggle,
        )
        is MainScreenState.Failure -> MainScreenFailure(currentState.message)
        is MainScreenState.Initial -> MainScreenLoading()
        is MainScreenState.Loading -> MainScreenLoading()
    }
}