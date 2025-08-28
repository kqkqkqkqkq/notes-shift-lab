package k.main_ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import k.ui_kit.R
import k.ui_models.models.NoteUio

@Composable
fun ExpandedSection(
    title: String,
    notes: List<NoteUio>,
    onNoteClick: (Long) -> Unit,
    onCompleteChange: (Long, Boolean) -> Unit,
    onChangePinned: (Long, Boolean) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(targetValue = if (expanded) -90f else 0f)

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                expanded = !expanded
            }
            .padding(
                vertical = 8.dp,
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Icon(
                painter = painterResource(R.drawable.chevron_left),
                contentDescription = "Expand",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.rotate(rotationAngle),
            )
        }

        AnimatedVisibility(visible = expanded) {
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
                if (notes.isEmpty()) {
                    item {
                        Text(
                            text = stringResource(k.main_ui.R.string.empty_pinned),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                } else {
                    items(notes) { note ->
                        NoteCard(
                            note = note,
                            onNoteClick = onNoteClick,
                            onCompleteChange = onCompleteChange,
                            onChangePinned = onChangePinned,
                        )
                    }
                }
            }
        }
    }
}