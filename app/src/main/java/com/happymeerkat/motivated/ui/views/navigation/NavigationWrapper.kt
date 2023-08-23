package com.happymeerkat.motivated.ui.views.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
    vm: MainVM = hiltViewModel()
) {
    val state = vm.homeUIState.collectAsState().value
    NavHost(
        navController = navController,
        route = NavigationGraph.GRAPHROOT.route,
        startDestination = NavigationGraph.HOME.route,
    ) {
        composable( route = NavigationGraph.FILTERS.route ) { Filters(modifier = modifier) }
        composable( route = NavigationGraph.HOME.route ) {
            Home(
                modifier = modifier,
                currentQuote = state.currentQuote,
                moveToNextQuote = {vm.moveToNextQuote()},
                moveToPreviousQuote = {vm.moveToPreviousQuote()}
            )
        }
        composable( route = NavigationGraph.SETTINGS.route ){ Settings(modifier = modifier) }
    }
}