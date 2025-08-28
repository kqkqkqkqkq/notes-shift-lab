package k.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(
    private val dataStore: DataStore<Preferences>,
) : ISettingsRepository {

    override val isDarkTheme: Flow<Boolean> =
        dataStore.data.map { prefs -> prefs[SettingsKeys.IS_DARK_THEME] ?: false }
    override val language: Flow<String> =
        dataStore.data.map { prefs -> prefs[SettingsKeys.LANGUAGE] ?: "en" }
    override val isNotification: Flow<Boolean> =
        dataStore.data.map { prefs -> prefs[SettingsKeys.IS_NOTIFICATION] ?: false }

    override suspend fun setDarkTheme(enabled: Boolean) {
        dataStore.edit { prefs -> prefs[SettingsKeys.IS_DARK_THEME] = enabled }
    }

    override suspend fun setLanguage(language: String) {
        dataStore.edit { prefs -> prefs[SettingsKeys.LANGUAGE] = language }
    }

    override suspend fun setNotification(enabled: Boolean) {
        dataStore.edit { prefs -> prefs[SettingsKeys.IS_NOTIFICATION] = enabled }
    }
}