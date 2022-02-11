package com.broprogramming.poco

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun ComposeNavigation(navController: NavHostController) {
    DestinationsNavHost(navController = navController, navGraph = NavGraphs.root)
}