package com.example.rickmorty.ui.episodes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rickmorty.data.EpisodeDTO
import com.example.rickmorty.ui.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesListScreen(
    navController: NavController,
    viewModel: EpisodesListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rick & Morty Episodes") }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (val state = uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is UiState.Success -> {
                    LazyColumn {
                        items(state.data) { episode ->
                            EpisodeItem(episode) {
                                navController.navigate("episode/${episode.id}")
                            }
                        }
                    }
                }
                is UiState.Error -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = state.message, color = MaterialTheme.colorScheme.error)
                        Button(onClick = { viewModel.loadEpisodes() }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EpisodeItem(episode: EpisodeDTO, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = episode.name, style = MaterialTheme.typography.titleLarge)
            Text(text = episode.episode, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
