package k.settings

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object SettingsKeys {
    val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")
    val LANGUAGE = stringPreferencesKey("language")
    val IS_NOTIFICATION = booleanPreferencesKey("is_notification")
}