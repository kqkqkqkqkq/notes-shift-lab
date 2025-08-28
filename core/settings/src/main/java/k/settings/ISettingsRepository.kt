package k.settings

import kotlinx.coroutines.flow.Flow

interface ISettingsRepository {
    val isDarkTheme: Flow<Boolean>
    val language: Flow<String>
    val isNotification: Flow<Boolean>

    suspend fun setDarkTheme(enabled: Boolean)
    suspend fun setLanguage(language: String)
    suspend fun setNotification(enabled: Boolean)
}