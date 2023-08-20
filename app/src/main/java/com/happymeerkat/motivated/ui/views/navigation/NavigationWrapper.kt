package com.happymeerkat.motivated.ui.views.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.happymeerkat.motivated.ui.views.filters.Filters
import com.happymeerkat.motivated.ui.views.home.Home
import com.happymeerkat.motivated.ui.views.settings.Settings

@Composable
fun NavigationWrapper(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = NavigationGraph.GRAPHROOT.route,
        startDestination = NavigationGraph.SETTINGS.route,
    ) {
        composable( route = NavigationGraph.FILTERS.route ) { Filters(modifier = modifier) }
        composable( route = NavigationGraph.HOME.route ) { Home(modifier = modifier) }
        composable( route = NavigationGraph.SETTINGS.route ){ Settings(modifier = modifier) }
    }
}