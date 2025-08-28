package k.main_ui.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import k.main_logic.MainAction
import k.main_logic.MainContentState
import k.main_logic.MainViewModel
import k.main_logic.utils.Filter
import k.main_logic.utils.filterNotes
import k.main_ui.R
import k.main_ui.components.ExpandedSection
import k.main_ui.components.FilterSection
import k.main_ui.components.MainBottomBar
import k.main_ui.components.MainTopBar
import k.main_ui.components.NoteCard
import k.main_ui.components.Settings
import k.ui_kit.theme.NotesshiftlabTheme
import k.ui_models.models.NoteUio
import k.ui_models.models.PriorityUio
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreenContent(
    content: MainContentState,
    onNoteClick: (Long) -> Unit,
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit,
    viewModel: MainViewModel = koinViewModel(),
) {
    var filters by remember { mutableStateOf(setOf<Filter>()) }
    val filteredNotes = filterNotes(content.notes, filters)
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.7f)
            ) {
                Settings(
                    isDarkTheme = isDarkTheme,
                    onThemeToggle = onThemeToggle,
                )
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                MainTopBar(
                    onSettingsClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            bottomBar = {
                MainBottomBar(
                    numberOfNotes = filteredNotes.size,
                    onCreateNote = {
                        viewModel.onAction(MainAction.CreateNote)
                    }
                )
            },
            contentWindowInsets = WindowInsets.statusBars,
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(WindowInsets.navigationBars)
            ) {
                ExpandedSection(
                    title = stringResource(R.string.pinned),
                    notes = content.notes.filter { it.pinned },
                    onNoteClick = onNoteClick,
                    onCompleteChange = { id, isCompleted ->
                        viewModel.onAction(MainAction.CompletedChange(id, isCompleted))
                    },
                    onChangePinned = { id, pinned ->
                        viewModel.onAction(MainAction.PinNote(id, pinned))
                    },
                )
                FilterSection(
                    selected = filters,
                    onFilterClick = { filter ->
                        filters = if (filter in filters) {
                            filters - filter
                        } else {
                            filters + filter
                        }
                    }
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(
                        horizontal = 4.dp,
                        vertical = 4.dp,
                    ),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (filteredNotes.isEmpty()) {
                        item {
                            Text(
                                text = stringResource(R.string.empty_notes),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        }
                    } else {
                        items(filteredNotes) { note ->
                            NoteCard(
                                note = note,
                                onNoteClick = onNoteClick,
                                onCompleteChange = { id, isCompleted ->
                                    viewModel.onAction(
                                        MainAction.CompletedChange(id, isCompleted)
                                    )
                                },
                                onChangePinned = { id, pinned ->
                                    viewModel.onAction(
                                        MainAction.PinNote(id, pinned)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
//@PreviewScreenSizes
@Composable
private fun MainScreenContentPreview(
    @PreviewParameter(MainScreenContentPreviewProvider::class) content: MainContentState,
) {
    NotesshiftlabTheme(darkTheme = true) {
        MainScreenContent(content = content, {}, false, {})
    }
}

class MainScreenContentPreviewProvider : PreviewParameterProvider<MainContentState> {
    override val values = sequenceOf(
        MainContentState(
            notes = listOf(
                NoteUio(
                    id = 0,
                    isComplete = false,
                    createdAt = 123,
                    updatedAt = 123,
                    pinned = false,
                    archived = false,
                    trashed = false,
                    title = "title",
                    text = "text",
                    completedAt = 123,
                    priority = PriorityUio.MEDIUM,
                    dueAt = 123,
                    reminderAt = 123,
                    colorHex = "0xff121212",
                ),
            ),
            selectedFilters = emptyList(),
        )
    )
}