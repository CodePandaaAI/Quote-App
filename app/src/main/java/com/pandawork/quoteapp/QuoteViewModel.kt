package com.pandawork.quoteapp

import androidx.lifecycle.ViewModel
import com.pandawork.quoteapp.data.QuoteUiState
import com.pandawork.quoteapp.data.imageList
import com.pandawork.quoteapp.data.quotesList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuoteViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(QuoteUiState())
    val uiState: StateFlow<QuoteUiState> = _uiState.asStateFlow()

    init {
        updateQuoteAndImage()
    }

    fun updateQuoteAndImage() {
        _uiState.value = QuoteUiState(
            quote = quotesList.random(),
            image = imageList.random()
        )
    }
}