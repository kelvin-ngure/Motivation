package com.happymeerkat.motivated.ui.views.navigation.bottom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.ui.views.filters.Filters
import com.happymeerkat.motivated.ui.views.home.Home
import com.happymeerkat.motivated.ui.views.navigation.NavigationGraph
import com.happymeerkat.motivated.ui.views.settings.Settings

@Composable
fun MainBottomNavigation(
    modifier: Modifier = Modifier,
    navigateToFavorites: () -> Unit,
    toggleFavorite: (quote:Quote) -> Unit,
    isFavorite: (quote: Quote) -> Boolean,
    quotes: List<Quote>,
    quotePage: Int,
    updateQuotePage: (page: Int) -> Unit,
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        route = "main",
        startDestination = NavigationGraph.HOME.route,
    ) {
        //composable( route = NavigationGraph.FILTERS.route ) { Filters(modifier = modifier) } TODO: remove after publishing version 1
        composable( route = NavigationGraph.HOME.route ) {
            Home(
                modifier = modifier,
                quotes = quotes,
                quotePage = quotePage,
                toggleFavorite = {quote -> toggleFavorite(quote)},
                isFavorite = {quote -> isFavorite(quote)},
                updateQuotePage = updateQuotePage
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