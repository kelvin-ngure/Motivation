package com.happymeerkat.motivated.ui.views.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.domain.repository.CategoryRepository
import com.happymeerkat.motivated.domain.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val quotesRepository: QuoteRepository,
    private val categoryRepository: CategoryRepository
): ViewModel() {
    private var _homeUIState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState())
    val homeUIState: StateFlow<HomeUIState> = _homeUIState

    var getQuotesJob: Job? = null
    var getCategoriesJob: Job? = null

    init {
        getAllQuotes()
    }

    private fun getAllQuotes() {
        getQuotesJob?.cancel()
        getQuotesJob = quotesRepository
            .getAllQuotes()
            .onEach { quoteList ->
                _homeUIState.value = _homeUIState.value.copy(
                    quotes = quoteList,
                    currentQuote = quoteList[0],
                    currentQuoteIndex = 0
                )
            }
            .launchIn(viewModelScope)
    }

    fun moveToNextQuote() {
        _homeUIState.value  = homeUIState.value.copy(
            currentQuoteIndex = (homeUIState.value.currentQuoteIndex + 1) % (homeUIState.value.quotes.size),
            currentQuote = homeUIState.value.quotes[homeUIState.value.currentQuoteIndex+1]
        )
    }

    fun moveToPreviousQuote() {
        _homeUIState.value  = homeUIState.value.copy(
            currentQuoteIndex = (homeUIState.value.currentQuoteIndex - 1) % (homeUIState.value.quotes.size),
            currentQuote = homeUIState.value.quotes[homeUIState.value.currentQuoteIndex-1]
        )
    }

}

data class HomeUIState(
    val quotes: List<Quote> = listOf(Quote(6000, quote = "default")),
    var currentQuoteIndex: Int = 0,
    var currentQuote: Quote = Quote(id = 0, quote = "", author = "", context = "", categoryId = 1, favorite = false)
)