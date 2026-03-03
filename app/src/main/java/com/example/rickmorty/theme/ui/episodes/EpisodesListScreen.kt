package com.example.rickmorty.theme.ui.episodes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rickmorty.ui.fake.FakeData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rick & Morty Episodes") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(FakeData.fakeEpisodes) { episode ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("episode/${episode.id}")
                        }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = episode.name, style = MaterialTheme.typography.titleLarge)
                        Text(text = episode.code, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
