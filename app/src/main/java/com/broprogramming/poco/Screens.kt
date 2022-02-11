package com.broprogramming.poco

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Cookbook : Screens("cookbook")
    object Recipe : Screens("recipe")
}