package com.broprogramming.poco.drawer

/*@Composable
fun DrawerLayout(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {
    val items = listOf(
        NavDrawerItem.Home,
        NavDrawerItem.Cookbook
    )*/

/*    Column{
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
                navController.navigateTo(scaffoldState.dynamicNavigation(item.route)){//item.route){
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
}*/

/*
private fun ScaffoldState.dynamicNavigation(route: String): Direction {
    //var directionDestination: DirectionDestination = HomeScreenDestination()
    when (route) {
    //    NavDrawerItem.Home.route -> directionDestination =  HomeScreenDestination(scaffoldState = this)
    //    NavDrawerItem.Cookbook.route -> directionDestination = RecipeListUiDestination()
    }
    //return directionDestination
}*/
