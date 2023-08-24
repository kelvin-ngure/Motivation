package com.happymeerkat.motivated.ui.views.navigation

import android.util.Log
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
import kotlinx.coroutines.launch
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
                    quotes = quoteList.shuffled(),
                    currentQuote = quoteList[0],
                    currentQuoteIndex = 0
                )
            }
            .launchIn(viewModelScope)
    }

    fun toggleFavorite(quote: Quote) {
        viewModelScope.launch {
            val favorite = quote.favorite
            val quote = quote.copy(
                favorite = !favorite
            )
            quotesRepository.upsertQuote(
                quote
            )
            Log.d("UPSERT", "UPDATED $favorite")
        }
    }

}

data class HomeUIState(
    val quotes: List<Quote> = listOf(Quote(6000, quote = "default")),
    var currentQuoteIndex: Int = 0,
    var currentQuote: Quote = Quote(id = 0, quote = "", author = "", context = "", categoryId = 1, favorite = false)
)