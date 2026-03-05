package com.example.rickmorty.ui.episodes

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rickmorty.ui.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeDetailScreen(
    navController: NavController,
    episodeId: Int?,
    viewModel: EpisodesListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Episode Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (val state = uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is UiState.Success -> {
                    val episode = state.data.find { it.id == episodeId }
                    if (episode != null) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "Name: ${episode.name}", style = MaterialTheme.typography.headlineMedium)
                            Text(text = "Air Date: ${episode.airDate}", style = MaterialTheme.typography.bodyLarge)
                            Text(text = "Code: ${episode.episode}", style = MaterialTheme.typography.bodyLarge)
                        }
                    } else {
                        Text(text = "Episode not found", modifier = Modifier.align(Alignment.Center))
                    }
                }
                is UiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center).padding(16.dp)
                    )
                }
            }
        }
    }
}
