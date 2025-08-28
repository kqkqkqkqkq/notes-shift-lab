package k.main_ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import k.main_logic.utils.Filter
import k.main_ui.R

@Composable
fun FilterSection(
    availableFilters: List<Filter> = Filter.entries,
    selected: Set<Filter>,
    onFilterClick: (Filter) -> Unit,
) {
    val labels = stringArrayResource(R.array.filters_array)
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = availableFilters) { filter ->
            val label = labels.getOrNull(filter.ordinal) ?: filter.name
            FilterChip(
                label = label,
                selected = filter in selected,
                onClick = { onFilterClick(filter) }
            )
        }
    }
}
