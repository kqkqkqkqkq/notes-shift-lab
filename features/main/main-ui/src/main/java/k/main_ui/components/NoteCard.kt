package k.main_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import k.ui_kit.R
import k.ui_kit.theme.Andika
import k.ui_kit.theme.green
import k.ui_kit.theme.red
import k.ui_kit.theme.yellow
import k.ui_kit.utils.AnimatedStrikethroughText
import k.ui_models.models.NoteUio
import k.ui_models.models.PriorityUio

@Composable
fun NoteCard(
    note: NoteUio,
    onNoteClick: (Long) -> Unit,
    onCompleteChange: (Long, Boolean) -> Unit,
    onChangePinned: (Long, Boolean) -> Unit,
) {
    var isCompleted by remember { mutableStateOf(note.isComplete) }
    var pinned by remember { mutableStateOf(note.pinned) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp,
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                onNoteClick(note.id)
            },
        shape = MaterialTheme.shapes.extraSmall,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Checkbox(
                    checked = isCompleted,
                    onCheckedChange = {
                        isCompleted = !isCompleted
                        onCompleteChange(note.id, isCompleted)
                    },
                    modifier = Modifier
                        .size(24.dp)
                )
                AnimatedStrikethroughText(
                    text = note.title ?: "",
                    modifier = Modifier.weight(1f),
                    isVisible = isCompleted,
                    style = MaterialTheme.typography.titleSmall,
                    strikethroughStyle = SpanStyle(
                        color = Color.Black.copy(alpha = 0.32f)
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                )
                IconButton(
                    onClick = {
                        pinned = !pinned
                        onChangePinned(note.id, pinned)
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(if (!pinned) R.drawable.pin else R.drawable.pin_filled),
                        contentDescription = "Pin",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }

            if (note.text != null) {
                Text(
                    text = note.text!!,
                    style = MaterialTheme.typography.bodySmall.copy(fontFamily = Andika),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                if (note.colorHex != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(12.dp)
                            .background(
                                color = Color(note.colorHex!!.toColorInt()),
                                shape = MaterialTheme.shapes.medium,
                            )
                    )
                }
                if (note.priority != null) {
                    val priorityText = when (note.priority!!) {
                        PriorityUio.LOW -> stringResource(k.main_ui.R.string.priority_low)
                        PriorityUio.MEDIUM -> stringResource(k.main_ui.R.string.priority_medium)
                        PriorityUio.HIGH -> stringResource(k.main_ui.R.string.priority_high)
                    }
                    val priorityColor = when (note.priority!!) {
                        PriorityUio.LOW -> green
                        PriorityUio.MEDIUM -> yellow
                        PriorityUio.HIGH -> red
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                priorityColor.copy(0.5f),
                                shape = MaterialTheme.shapes.medium,
                            )
                    ) {
                        Text(
                            text = priorityText,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(horizontal = 8.dp),
                        )
                    }
                }
            }
        }
    }
}