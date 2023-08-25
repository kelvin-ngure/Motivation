package com.happymeerkat.motivated.ui.views.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.happymeerkat.motivated.data.models.Quote

@Composable
fun MainScreen(
    navigateToFavorites: () -> Unit,
    toggleFavorite: (quote: Quote) -> Unit,
    quotes: List<Quote>,
    navHostController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navHostController) }
    ) { _ ->
        MainBottomNavigation(
            navigateToFavorites = navigateToFavorites,
            toggleFavorite = { quote -> toggleFavorite(quote) },
            quotes = quotes,
            navHostController = navHostController
        )
    }
}