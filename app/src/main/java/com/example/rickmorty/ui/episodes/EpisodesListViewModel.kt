package com.example.rickmorty.ui.episodes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.ApiClient
import com.example.rickmorty.data.EpisodeDTO
import com.example.rickmorty.data.FavoritesManager
import com.example.rickmorty.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EpisodesListViewModel(application: Application) : AndroidViewModel(application) {

    private val favoritesManager = FavoritesManager(application)
    
    private val _uiState = MutableStateFlow<UiState<List<EpisodeDTO>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<EpisodeDTO>>> = _uiState.asStateFlow()

    val favoriteEpisodes: StateFlow<Set<String>> = favoritesManager.favoriteEpisodes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    init {
        loadEpisodes()
    }

    fun loadEpisodes() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = ApiClient.rickMortyApi.getEpisodes()
                _uiState.value = UiState.Success(response.results)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error al cargar episodios")
            }
        }
    }

    fun toggleFavorite(episodeId: String) {
        viewModelScope.launch {
            favoritesManager.toggleFavorite(episodeId)
        }
    }
}
