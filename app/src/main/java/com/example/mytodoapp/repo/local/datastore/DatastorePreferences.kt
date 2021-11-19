package com.example.mytodoapp.repo.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.mytodoapp.view.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DatastorePreferences(val context: Context) {
    private val jwt = stringPreferencesKey("jwt")
    suspend fun setJWT(jwt: String) {
        context.dataStore.edit { preferences ->
            preferences[this.jwt] = jwt
        }
    }

    suspend fun getJWT(): String? {
        return context.dataStore.data.map {
            it[jwt]
        }.first()
    }
}