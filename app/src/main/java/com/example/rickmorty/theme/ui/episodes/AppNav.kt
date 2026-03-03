package com.example.rickmorty.theme.ui.episodes

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val episodeId = backStackEntry.arguments?.getString("id")
            EpisodeDetailScreen(navController, episodeId)
        }
    }
}
