package k.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import k.detail_ui.DetailScreen
import k.main_ui.MainScreen
import k.ui_kit.theme.NotesshiftlabTheme

@Composable
fun NotesNavigation() {
    var isDarkTheme by rememberSaveable { mutableStateOf(false) }

    NotesshiftlabTheme(darkTheme = isDarkTheme) {
        val backStack = rememberNavBackStack<Screen>(Screen.Main)

        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
                rememberSceneSetupNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Screen.Main> {
                    MainScreen(
                        isDarkTheme = isDarkTheme,
                        onThemeToggle = {
                            isDarkTheme = !isDarkTheme
                        },
                        onNoteClick = { id ->
                            backStack.add(Screen.Detail(id))
                        },
                    )
                }
                entry<Screen.Detail> { key ->
                    DetailScreen(
                        id = key.id,
                        onBackClick = { backStack.removeLastOrNull() },
                    )
                }
            }
        )
    }
}