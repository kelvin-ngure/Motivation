package com.happymeerkat.motivated.ui.views.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymeerkat.motivated.R
import com.happymeerkat.motivated.data.models.Favorite
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.data.models.Theme
import com.happymeerkat.motivated.domain.repository.FavoriteRepository
import com.happymeerkat.motivated.domain.repository.QuoteRepository
import com.happymeerkat.motivated.domain.themes.ThemeManager
import com.happymeerkat.motivated.domain.themes.ThemeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val quotesRepository: QuoteRepository,
    private val favoriteRepository: FavoriteRepository,
    private val themeManager: ThemeManager
): ViewModel() {
    private var _homeUIState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState())
    val homeUIState: StateFlow<HomeUIState> = _homeUIState

    var getQuotesJob: Job? = null
    var getCategoriesJob: Job? = null
    var getFavoritesJob: Job? = null
    var getFontJob: Job? = null
    var getThemeJob: Job? = null

    init {
        getAllQuotes()
        getAllFavorites()
        getTheme()
    }

    private fun getAllQuotes() {
        getQuotesJob?.cancel()
        getQuotesJob = quotesRepository
            .getAllQuotes()
            .onEach { quoteList ->
                _homeUIState.value = _homeUIState.value.copy(
                    quotes = quoteList.shuffled(),
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getAllFavorites() {
        getFavoritesJob?.cancel()
        getFavoritesJob = favoriteRepository
            .getAllFavorites()
            .onEach { favorites ->
                _homeUIState.value = homeUIState.value.copy(
                    favorites = favorites
                )
            }
            .launchIn(viewModelScope)
    }


    private fun getTheme() {
        getThemeJob?.cancel()
        getThemeJob = themeManager
            .currentThemeIndex
            .onEach { backgroundIndex ->
                _homeUIState.value = homeUIState.value.copy(
                    background = themeManager.themes[backgroundIndex],
                    backgroundIndex = backgroundIndex
                )
            }
            .launchIn(viewModelScope)
    }



    fun toggleFavorite(quote: Quote) {
        viewModelScope.launch {
            val favorite = Favorite(quote.id)
            if(_homeUIState.value.favorites.contains(favorite)){
                favoriteRepository.deleteFavorite(favorite)
            } else {
                favoriteRepository.insertFavorite(favorite)
            }
        }
    }

    fun quoteInFavorites(quote: Quote): Boolean {
        return _homeUIState.value.favorites.contains(Favorite(quote.id))
    }

    fun updateQuotePage(page: Int) {
        _homeUIState.value = homeUIState.value.copy(
            quotePage = page
        )
    }

    fun showThemeGroups(): HashMap<ThemeType, List<Int>> = themeManager.getThemeGroups()
}

data class HomeUIState(
    val quotes: List<Quote> = listOf(Quote(6000, quote = "")),
    var currentQuoteIndex: Int = 0,
    var currentQuote: Quote = Quote(id = 0, quote = "", author = "", context = "", categoryId = 1, favorite = false),
    var favorites: List<Favorite> = emptyList(),
    val quotePage: Int = 0,
    val backgroundIndex: Int = 0,
    val background: Theme = Theme(themeId = 1000, backgroundImage = null, backgroundColor = null, fontColor = null, fontId = null, awsKey = null),
    val backgrounds: List<Theme> = emptyList()
)