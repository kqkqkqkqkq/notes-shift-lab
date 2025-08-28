package k.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import k.settings.constants.Constants.DATASTORE_NAME

fun createDataStore(context: Context): DataStore<Preferences> = PreferenceDataStoreFactory.create(
    produceFile = { context.preferencesDataStoreFile(DATASTORE_NAME) }
)