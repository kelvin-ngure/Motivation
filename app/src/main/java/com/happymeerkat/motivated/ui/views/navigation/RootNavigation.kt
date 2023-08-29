package com.happymeerkat.motivated.ui.views.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.happymeerkat.motivated.data.models.Favorite
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.ui.views.home.Home
import com.happymeerkat.motivated.ui.views.settings.Settings
import com.happymeerkat.motivated.ui.views.settings.favorites.Favorites
import com.happymeerkat.motivated.ui.views.settings.fonts.Fonts
import com.happymeerkat.motivated.ui.views.vm.MainVM

@Composable
fun RootNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    vm: MainVM = hiltViewModel()
) {
    val state = vm.homeUIState.collectAsState().value
    NavHost(
        navController = navController,
        route = NavigationGraph.GRAPHROOT.route,
        startDestination = NavigationGraph.HOME.route,
    ) {
        composable( route = NavigationGraph.HOME.route ) {
            Home(
                quotes = state.quotes,
                quotePage = state.quotePage,
                isFavorite = {quote:Quote -> vm.quoteInFavorites(quote)},
                toggleFavorite = {quote -> vm.toggleFavorite(quote)},
                updateQuotePage = {page: Int -> vm.updateQuotePage(page)},
                fontId = state.fontId,
                theme = state.background,
                navigateToSettings = {navController.navigate(NavigationGraph.SETTINGS.route)}
            )
        }

        composable( route = NavigationGraph.SETTINGS.route ){
            Settings(
                modifier = modifier,
                navigateToFavorites = {navController.navigate(NavigationGraph.FAVORITES.route)},
                navigateToFonts = {navController.navigate(NavigationGraph.FONTS.route)}
            )
        }

        composable( route = NavigationGraph.FAVORITES.route ){
            Favorites(
                backToSettings = {navController.popBackStack()},
                favoriteQuotes = state.quotes.filter { quote -> state.favorites.contains(Favorite(quote.id)) },
                toggleFavorite = {quote: Quote -> vm.toggleFavorite(quote)}
            )
        }

        composable( route = NavigationGraph.FONTS.route ){
            Fonts(
                backToSettings = {navController.popBackStack()}
            )
        }
    }
}

