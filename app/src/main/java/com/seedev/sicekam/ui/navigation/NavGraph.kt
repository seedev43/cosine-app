package com.seedev.sicekam.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.seedev.sicekam.ui.screen.AboutScreen
import com.seedev.sicekam.ui.screen.CheckSimilarityScreen
import com.seedev.sicekam.ui.screen.HistoryScreen
import com.seedev.sicekam.ui.screen.HomeScreen

@ExperimentalMaterial3Api
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable(Routes.HOME) {
            HomeScreen(navController)
        }
        composable(Routes.SIMILARITY) {
            CheckSimilarityScreen(navController)
        }
        composable(Routes.HISTORY) {
            HistoryScreen(navController)
        }
        composable(Routes.ABOUT) {
            AboutScreen(navController)
        }
    }
}