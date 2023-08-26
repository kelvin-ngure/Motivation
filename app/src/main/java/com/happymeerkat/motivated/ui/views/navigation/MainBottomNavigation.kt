package com.happymeerkat.motivated.ui.views.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.ui.views.filters.Filters
import com.happymeerkat.motivated.ui.views.home.Home
import com.happymeerkat.motivated.ui.views.settings.Settings
import com.happymeerkat.motivated.ui.views.settings.favorites.Favorites

@Composable
fun MainBottomNavigation(
    modifier: Modifier = Modifier,
    navigateToFavorites: () -> Unit,
    toggleFavorite: (quote:Quote) -> Unit,
    isFavorite: (quote: Quote) -> Boolean,
    quotes: List<Quote>,
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        route = "main",
        startDestination = NavigationGraph.HOME.route,
    ) {
        composable( route = NavigationGraph.FILTERS.route ) { Filters(modifier = modifier) }
        composable( route = NavigationGraph.HOME.route ) {
            Home(
                modifier = modifier,
                quotes = quotes,
                toggleFavorite = {quote -> toggleFavorite(quote)},
                isFavorite = {quote -> isFavorite(quote)}
            )
        }
        composable( route = NavigationGraph.SETTINGS.route ){
            Settings(
                modifier = modifier,
                goToFavorites = {navigateToFavorites()}
            )
        }
    }
}