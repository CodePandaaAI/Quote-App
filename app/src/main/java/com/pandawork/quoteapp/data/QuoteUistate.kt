package com.pandawork.quoteapp.data

data class QuoteUiState(
    val quote: String = quotesList[0], // Initial quote
    val image: Int = imageList[0],    // Initial image
    val canGoPrevious: Boolean = false
)