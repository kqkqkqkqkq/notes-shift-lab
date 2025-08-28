package k.detail_ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import k.detail_logic.DetailScreenState
import k.detail_logic.DetailViewModel
import k.detail_ui.state.DetailScreenContent
import k.detail_ui.state.DetailScreenFailure
import k.detail_ui.state.DetailScreenLoading
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailScreen(
    id: Long,
    onBackClick: () -> Unit,
) {
    DetailScreenUi(
        id = id,
        onBackClick = onBackClick,
    )
}

@Composable
fun DetailScreenUi(
    id: Long,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = koinViewModel(parameters = { parametersOf(id) }),
) {
    val state by viewModel.state.collectAsState()
    when (val currentState = state) {
        is DetailScreenState.Content -> DetailScreenContent(currentState.content, onBackClick)
        is DetailScreenState.Failure -> DetailScreenFailure(currentState.message)
        is DetailScreenState.Initial -> DetailScreenLoading()
        is DetailScreenState.Loading -> DetailScreenLoading()
    }
}