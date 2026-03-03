package com.example.rickmorty.ui.episodes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rickmorty.ui.fake.FakeData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeDetailScreen(navController: NavController, episodeId: String? = null) {
    val episode = FakeData.fakeEpisodes.find { it.id.toString() == episodeId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = episode?.name ?: "Episode Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            if (episode != null) {
                Text(text = "Name: ${episode.name}", style = MaterialTheme.typography.headlineMedium)
                Text(text = "Air Date: ${episode.airDate}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Code: ${episode.code}", style = MaterialTheme.typography.bodyLarge)
            } else {
                Text(text = "Episode not found")
            }
        }
    }
}
