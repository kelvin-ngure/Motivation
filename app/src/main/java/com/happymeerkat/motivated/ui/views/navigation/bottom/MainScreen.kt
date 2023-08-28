package com.happymeerkat.motivated.ui.views.navigation.bottom

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.ui.views.navigation.bottom.BottomBar
import com.happymeerkat.motivated.ui.views.navigation.bottom.MainBottomNavigation

@Composable
fun MainScreen(
    navigateToFavorites: () -> Unit,
    toggleFavorite: (quote: Quote) -> Unit,
    isFavorite: (quote: Quote) -> Boolean,
    quotes: List<Quote>,
    quotePage: Int,
    updateQuotePage: (page: Int) -> Unit,
    fontId: Int,
    navHostController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navHostController) }
    ) { _ ->
        MainBottomNavigation(
            navigateToFavorites = navigateToFavorites,
            toggleFavorite = { quote -> toggleFavorite(quote) },
            isFavorite = {quote: Quote -> isFavorite(quote)},
            quotes = quotes,
            quotePage = quotePage,
            navHostController = navHostController,
            updateQuotePage = updateQuotePage,
            fontId = fontId
        )
    }
}