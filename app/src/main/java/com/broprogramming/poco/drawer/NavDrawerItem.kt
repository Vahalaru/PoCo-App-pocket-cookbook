package com.broprogramming.poco.drawer

import com.broprogramming.poco.R

sealed class NavDrawerItem(var route: String, var icon: Int, var title: String){
    object Home : NavDrawerItem("home", R.drawable.ic_home, "Home")
    object Cookbook : NavDrawerItem("cookbook", R.drawable.ic_cookbook, "Cookbook")
}