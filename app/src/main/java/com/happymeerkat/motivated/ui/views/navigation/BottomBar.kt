package com.happymeerkat.motivated.ui.views.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val screens = listOf(NavigationGraph.FILTERS, NavigationGraph.HOME, NavigationGraph.SETTINGS)
    val currentScreen = currentRoute(navController)

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentScreen == screen.route,
                onClick = {
                    if (currentScreen != screen.route) {
                        navController.navigate(screen.route)
                    }
                },
                icon = { screen.icon?.let { Icon(imageVector = it, contentDescription = screen.route) } }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}