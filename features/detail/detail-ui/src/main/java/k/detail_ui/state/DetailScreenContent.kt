package k.detail_ui.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import k.detail_logic.DetailAction
import k.detail_logic.DetailContentState
import k.detail_logic.DetailViewModel
import k.detail_ui.components.DetailBottomBar
import k.detail_ui.components.DetailTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreenContent(
    content: DetailContentState,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = koinViewModel(),
) {

    var title by remember { mutableStateOf(content.note.title ?: "") }
    var text by remember { mutableStateOf(content.note.text ?: "") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            DetailTopBar(
                onBackClick = {
                    onBackClick()
                    viewModel.onAction(DetailAction.UpdateText(text))
                    viewModel.onAction(DetailAction.UpdateTitle(title))
                },
            )
        },
        bottomBar = {
            DetailBottomBar(
                onSaveClick = {
                    viewModel.onAction(DetailAction.UpdateText(text))
                    viewModel.onAction(DetailAction.UpdateTitle(title))
                },
                onDeleteClick = {
                    viewModel.onAction(
                        DetailAction.MoveToTrash
                    )
                },
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
            BasicTextField(
                value = title,
                onValueChange = {
                    title = it
                },
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default,
                keyboardActions = KeyboardActions.Default,
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
            )

            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default,
                keyboardActions = KeyboardActions.Default,
//                decorationBox = { innerTextField ->
//                    innerTextField()
//                }
            )
        }
    }
}