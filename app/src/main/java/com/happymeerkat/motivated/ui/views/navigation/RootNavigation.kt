package com.happymeerkat.motivated.ui.views.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.happymeerkat.motivated.ui.views.settings.themes.Themes
import com.happymeerkat.motivated.ui.views.vm.MainVM

@Composable
fun RootNavigation(
) {
    val navController: NavHostController = rememberNavController()
    val vm: MainVM = hiltViewModel()
    val state = vm.homeUIState.collectAsState().value
    val context = LocalContext.current

    NavHost(
        navController = navController,
        route = NavigationGraph.GRAPHROOT.route,
        startDestination = NavigationGraph.HOME.route,
    ) {
        composable( route = NavigationGraph.HOME.route ) {
            Home(
                context = context,
                quotes = state.quotes,
                quotePage = state.quotePage,
                isFavorite = { quote:Quote -> vm.quoteInFavorites(quote)},
                toggleFavorite = { quote -> vm.toggleFavorite(quote)},
                updateQuotePage = { page: Int -> vm.updateQuotePage(page)},
                theme = state.currentTheme
            ) { navController.navigate(NavigationGraph.SETTINGS.route) }
        }

        composable( route = NavigationGraph.SETTINGS.route ){
            Settings(
                modifier = Modifier.statusBarsPadding()
                    .background(MaterialTheme.colorScheme.background),
                context = context,
                navigateToFavorites = {navController.navigate(NavigationGraph.FAVORITES.route)},
                navigateToFonts = {navController.navigate(NavigationGraph.THEMES.route)},
                backToHome = {navController.popBackStack()}
            )
        }

        composable( route = NavigationGraph.FAVORITES.route ){
            Favorites(
                modifier = Modifier.statusBarsPadding(),
                backToSettings = {navController.popBackStack()},
                favoriteQuotes = state.quotes.filter { quote -> state.favorites.contains(Favorite(quote.id)) },
                toggleFavorite = {quote: Quote -> vm.toggleFavorite(quote)}
            )
        }

        composable( route = NavigationGraph.THEMES.route ){
            Themes(
                modifier = Modifier.statusBarsPadding()
                    .background(MaterialTheme.colorScheme.background),
                backToSettings = {navController.popBackStack()},
                themes = state.themes,
                currentTheme = state.currentTheme,
                changeCurrentTheme = {theme -> vm.changeCurrentTheme(theme)}
            )
        }
    }
}

