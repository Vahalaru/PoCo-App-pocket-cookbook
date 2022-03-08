package com.broprogramming.poco.drawer

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AndroidUiDispatcher.Companion.Main
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.broprogramming.poco.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu,"Navigation Drawer")
            }
        },
        backgroundColor = colorResource(id = R.color.purple_200),
        contentColor = Color.White,
        actions = {
            IconButton(onClick = {
                //TODO: Add Menu action
            }) {
                Icon(Icons.Filled.Settings,"Settings")
            }
        }
    )
}

@Composable
@Preview
fun TopBarPreview() {
    TopBar(scope = CoroutineScope(Main), scaffoldState = rememberScaffoldState())
}