package com.happymeerkat.motivated.ui.views.home

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
class HomeVM @Inject constructor(
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
                    quotes = quoteList
                )
            }
            .launchIn(viewModelScope)
    }

    fun moveToNextQuote() {
        _homeUIState.value  = homeUIState.value.copy(
            currentQuoteIndex = (homeUIState.value.currentQuoteIndex + 1) % (homeUIState.value.quotes.size),
            currentQuote = homeUIState.value.quotes[homeUIState.value.currentQuoteIndex]
        )
    }

    fun moveToPreviousQuote() {
        _homeUIState.value  = homeUIState.value.copy(
            currentQuoteIndex = (homeUIState.value.currentQuoteIndex - 1) % (homeUIState.value.quotes.size),
            currentQuote = homeUIState.value.quotes[homeUIState.value.currentQuoteIndex]
        )
    }

}

data class HomeUIState(
    val quotes: List<Quote> = emptyList(),
    var currentQuoteIndex: Int = 0,
    var currentQuote: Quote? = Quote(id = 0, quote = "default", author = "fsdfd", context = "ewrw", categoryId = 1, favorite = false)
)