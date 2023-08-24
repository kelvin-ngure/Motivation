package com.happymeerkat.motivated.ui.views.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.ui.views.filters.Filters
import com.happymeerkat.motivated.ui.views.home.Home
import com.happymeerkat.motivated.ui.views.settings.Settings
import com.happymeerkat.motivated.ui.views.settings.favorites.Favorites

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
                quotes = state.quotes,
                toggleFavorite = {quote -> vm.toggleFavorite(quote)}
            )
        }
        composable( route = NavigationGraph.SETTINGS.route ){
            Settings(
                modifier = modifier,
                goToFavorites = {navController.navigate(NavigationGraph.FAVORITES.route)}
            )
        }
        composable( route = NavigationGraph.FAVORITES.route ){
            Favorites(
                backToSettings = {navController.popBackStack()},
                favoriteQuotes = state.quotes.filter {
                    it.favorite
                },
                toggleFavorite = {quote: Quote -> vm.toggleFavorite(quote)}
            )
        }
    }
}