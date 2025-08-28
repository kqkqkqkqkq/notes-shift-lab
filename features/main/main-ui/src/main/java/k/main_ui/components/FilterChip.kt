package k.main_ui.components

import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    InputChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
        },
        modifier = Modifier,
        border = InputChipDefaults.inputChipBorder(
            enabled = true,
            selected = selected,
            borderColor = MaterialTheme.colorScheme.onBackground,
            borderWidth = 1.dp,
        ),
        colors = InputChipDefaults.inputChipColors(
            containerColor = MaterialTheme.colorScheme.background,
            labelColor = MaterialTheme.colorScheme.onBackground,
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
        )
    )
}