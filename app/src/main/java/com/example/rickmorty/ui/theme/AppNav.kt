package com.example.rickmorty.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickmorty.ui.episodes.EpisodeDetailScreen
import com.example.rickmorty.ui.episodes.EpisodesListScreen

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "episodes"
    ) {
        composable("episodes") {
            EpisodesListScreen(navController)
        }
        composable(
            route = "episode/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val episodeId = backStackEntry.arguments?.getInt("id")
            EpisodeDetailScreen(navController, episodeId)
        }
    }
}
