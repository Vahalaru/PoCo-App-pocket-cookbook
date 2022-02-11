package com.broprogramming.poco

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.broprogramming.poco.ui.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun ComposeNavigation(navController: NavHostController) {
    DestinationsNavHost(navController = navController, navGraph = NavGraphs.root)
}