package com.pandawork.quoteapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandawork.quoteapp.data.QuoteUiState
import com.pandawork.quoteapp.data.imageList // Assuming imageList is accessible
import com.pandawork.quoteapp.data.quotesList // Assuming quotesList is accessible
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class QuoteViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuoteUiState())
    val uiState: StateFlow<QuoteUiState> = _uiState.asStateFlow()

    // History of displayed quote/image pairs (index in original lists)
    private val history = mutableStateListOf<Pair<Int, Int>>()
    private var currentHistoryIndex = -1

    init {
        // Initialize with the first quote/image
        updateQuoteAndImage(isNext = true, isInitial = true)
    }

    fun updateQuoteAndImage(isNext: Boolean = true, isInitial: Boolean = false) {
        viewModelScope.launch { // Use viewModelScope for coroutines
            if (isNext) {
                // If it's the initial load or we are moving forward
                if (isInitial || currentHistoryIndex == history.size - 1) {
                    val newQuoteIndex = Random.nextInt(quotesList.size)
                    val newImageIndex = Random.nextInt(imageList.size)
                    history.add(Pair(newQuoteIndex, newImageIndex))
                    currentHistoryIndex++
                } else {
                    // Moving forward in history
                    currentHistoryIndex++
                }
            } else { // Moving to previous
                if (currentHistoryIndex > 0) {
                    currentHistoryIndex--
                }
            }

            if (history.isNotEmpty() && currentHistoryIndex >= 0 && currentHistoryIndex < history.size) {
                val (quoteIndex, imageIndex) = history[currentHistoryIndex]
                _uiState.update { currentState ->
                    currentState.copy(
                        quote = quotesList[quoteIndex],
                        image = imageList[imageIndex],
                        canGoPrevious = currentHistoryIndex > 0
                    )
                }
            }
        }
    }

    fun nextQuote() {
        updateQuoteAndImage(isNext = true)
    }

    fun previousQuote() {
        if (uiState.value.canGoPrevious) {
            updateQuoteAndImage(isNext = false)
        }
    }
}