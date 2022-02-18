package com.broprogramming.poco.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.broprogramming.poco.R
import com.broprogramming.poco.ui.destinations.DirectionDestination
import com.broprogramming.poco.ui.destinations.HomeScreenDestination
import com.broprogramming.poco.ui.destinations.RecipeListUiDestination
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.Direction
import kotlinx.coroutines.CoroutineScope

@Composable
fun DrawerLayout(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {
    val items = listOf(
        NavDrawerItem.Home,
        NavDrawerItem.Cookbook
    )

    Column{
        Image(
            painter = painterResource(id = R.drawable.ic_my_launcher),
            contentDescription = "",
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(10.dp)
        )
        Spacer(
            modifier = Modifier.fillMaxWidth().height(5.dp)
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            DrawerItem(item = item, selected = currentRoute == item.route, onItemClick =  {
                //navController.navigateTo(dynamicNavigation(item.route))//{
                navController.navigateTo(dynamicNavigation(item.route)){//item.route){
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route){
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
        }
    }
}

private fun dynamicNavigation(route: String): Direction {
    var directionDestination: DirectionDestination = HomeScreenDestination()
    when (route) {
        NavDrawerItem.Home.route -> directionDestination =  HomeScreenDestination()
        NavDrawerItem.Cookbook.route -> directionDestination = RecipeListUiDestination()
    }
    return directionDestination
}