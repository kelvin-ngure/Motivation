package com.happymeerkat.motivated.ui.views.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.happymeerkat.motivated.data.models.Favorite
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.ui.views.navigation.bottom.MainScreen
import com.happymeerkat.motivated.ui.views.settings.favorites.Favorites
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
        startDestination = "main",
    ) {
        composable( route = "main" ) {
            MainScreen(
                quotes = state.quotes,
                quotePage = state.quotePage,
                updateQuotePage = {page: Int -> vm.updateQuotePage(page)},
                toggleFavorite = {quote -> vm.toggleFavorite(quote)},
                isFavorite = {quote:Quote -> vm.quoteInFavorites(quote)},
                navigateToFavorites = {navController.navigate(NavigationGraph.FAVORITES.route)},
                fontId = state.fontId
            )
        }

        composable( route = NavigationGraph.FAVORITES.route ){
            Favorites(
                backToSettings = {navController.popBackStack()},
                favoriteQuotes = state.quotes.filter { quote -> state.favorites.contains(Favorite(quote.id)) },
                toggleFavorite = {quote: Quote -> vm.toggleFavorite(quote)}
            )
        }

    }
}

