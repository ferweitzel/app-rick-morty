package com.example.rickmorty.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "favorites")

class FavoritesManager(private val context: Context) {
    
    companion object {
        private val FAVORITES_KEY = stringSetPreferencesKey("favorite_episodes")
    }

    val favoriteEpisodes: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
            preferences[FAVORITES_KEY] ?: emptySet()
        }

    suspend fun toggleFavorite(episodeId: String) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITES_KEY] ?: emptySet()
            if (currentFavorites.contains(episodeId)) {
                preferences[FAVORITES_KEY] = currentFavorites - episodeId
            } else {
                preferences[FAVORITES_KEY] = currentFavorites + episodeId
            }
        }
    }
}
