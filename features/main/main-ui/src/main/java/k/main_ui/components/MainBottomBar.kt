package k.main_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import k.ui_kit.R

@Composable
fun MainBottomBar(
    numberOfNotes: Int,
    onCreateNote: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .blur(16.dp)
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
        )
        BottomAppBar(
            modifier = Modifier.matchParentSize(),
            containerColor = Color.Transparent,
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp,
                    )
            ) {
                Text(
                    text = stringResource(k.main_ui.R.string.number_of_notes, numberOfNotes),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.Center)
                )
                IconButton(
                    onClick = onCreateNote,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(36.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.pencil),
                        contentDescription = "Create note",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(2.dp)
                    )
                }
            }
        }
    }
}