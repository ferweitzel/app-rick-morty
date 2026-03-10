package com.example.rickmorty.ui.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.ApiClient
import com.example.rickmorty.data.CharacterDTO
import com.example.rickmorty.data.EpisodeDTO
import com.example.rickmorty.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class EpisodeDetailData(
    val episode: EpisodeDTO,
    val characters: List<CharacterDTO>
)

class EpisodeDetailViewModel : ViewModel() {

    private val _state = MutableStateFlow<UiState<EpisodeDetailData>>(UiState.Loading)
    val state: StateFlow<UiState<EpisodeDetailData>> = _state

    fun loadDetail(id: Int) {
        _state.value = UiState.Loading

        viewModelScope.launch {
            runCatching {
                val episode = ApiClient.rickMortyApi.getEpisodeById(id)
                val characters = episode.characters.map { url ->
                    ApiClient.rickMortyApi.getCharacterByUrl(url)
                }
                EpisodeDetailData(episode, characters)
            }.onSuccess { data ->
                _state.value = UiState.Success(data = data)
            }.onFailure { error ->
                _state.value = UiState.Error(message = error.message ?: "Error desconocido")
            }
        }
    }
}
