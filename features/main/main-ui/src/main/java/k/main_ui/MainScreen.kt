package k.main_ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat

@Composable
fun MainScreen(
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedLanguage by rememberSaveable { mutableStateOf("en") }

    val localeOptions = mapOf(
        "English" to "en",
        "Русский" to "ru",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(R.string.test), color = MaterialTheme.colorScheme.onBackground)

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Dark theme", color = MaterialTheme.colorScheme.onBackground)
            Spacer(Modifier.width(8.dp))
            Switch(
                checked = isDarkTheme,
                onCheckedChange = { onThemeToggle(it) }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box {
            Button(onClick = { expanded = true }) {
                Text("Language: $selectedLanguage")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                localeOptions.forEach { (label, tag) ->
                    DropdownMenuItem(
                        text = { Text(label) },
                        onClick = {
                            expanded = false
                            selectedLanguage = tag
                            AppCompatDelegate.setApplicationLocales(
                                LocaleListCompat.forLanguageTags(tag)
                            )
                        }
                    )
                }
            }
        }
    }
}