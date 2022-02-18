package com.broprogramming.poco

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.broprogramming.poco.model.Recipe
import com.broprogramming.poco.ui.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun ComposeNavigation(navController: NavHostController) {
/*    DestinationsNavHost(
        dependenciesContainerBuilder = {
            dependency(navController)
        },
        navGraph = NavGraphs.root
    )*/

    DestinationsNavHost(NavGraphs.root, navController = navController,
    dependenciesContainerBuilder = {
        dependency(Recipe)
    })
}