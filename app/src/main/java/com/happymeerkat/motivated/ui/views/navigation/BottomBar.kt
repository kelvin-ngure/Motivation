package com.happymeerkat.motivated.ui.views.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val screens = listOf(NavigationGraph.FILTERS, NavigationGraph.HOME, NavigationGraph.SETTINGS)
    val currentScreen = currentRoute(navController)

    NavigationBar(
        modifier = modifier.height(60.dp).background(MaterialTheme.colorScheme.background),
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        screens.forEach { screen ->
            NavigationBarItem(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                selected = currentScreen == screen.route,
                onClick = {
                    if (currentScreen != screen.route) {
                        navController.navigate(screen.route)
                    }
                },
                icon = { screen.icon?.let { Icon(imageVector = it, contentDescription = screen.route) } },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.background,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}