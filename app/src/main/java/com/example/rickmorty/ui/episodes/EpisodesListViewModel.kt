package com.example.rickmorty.ui.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.ApiClient
import com.example.rickmorty.data.EpisodeDTO
import com.example.rickmorty.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EpisodesListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<EpisodeDTO>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<EpisodeDTO>>> = _uiState.asStateFlow()

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
}
