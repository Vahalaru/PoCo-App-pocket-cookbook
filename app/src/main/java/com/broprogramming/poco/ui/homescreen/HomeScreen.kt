package com.broprogramming.poco.ui.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination

@Destination(route = "Home", start = true)
@Composable
fun HomeScreen() {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val menu = homeViewModel.menuItemsFlow.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        HomeScreenContent(menu)
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreenContent(menu: State<List<List<String>>>) {
    val pagerState = rememberPagerState()
    val menuItems = menu.value

    //TODO: change count to number user selects in settings sheet
    HorizontalPager(count = menuItems.size, state = pagerState) { menuItem ->
        MenuCard(menuItem.toString())
    }
}

@Composable
fun MenuCard(recipeId: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Text(text = recipeId)
    }
}