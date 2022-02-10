package com.broprogramming.poco.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppDrawer(
    //currentRoute: String = ""
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.height(24.dp))

        //Home Button
        TextButton(
            onClick = {
                //navController.navigate(MyDestination.HOME_PATH)
            }
        ) {
            Text(text = "Home")
        }

        //Cookbook Button
        TextButton(
            onClick = {
                //navController.navigate(MyDestination.COOKBOOK_PATH)
            }
        ) {
            Text(text = "Cookbook")
        }

        //Recipe Details Button
        TextButton(
            onClick = {
                //navController.navigate(MyDestination.RECIPE_PATH)
            }
        ) {
            Text(text = "Recipe")
        }
    }
}