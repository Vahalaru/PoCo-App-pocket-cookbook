package com.broprogramming.poco.ui.homescreen

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(scaffoldState: ScaffoldState, navController: NavHostController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()

}