package com.example.cinema_sukhorukova_shift_2026.presentation.ui.screens

import com.example.cinema_sukhorukova_shift_2026.domain.entity.Movie

sealed class AfishaScreenState {
    data object Initial : AfishaScreenState()
    data object Loading : AfishaScreenState()
    data class Content(val movies: List<Movie>) : AfishaScreenState()
    data class Error(val message: String) : AfishaScreenState()
}
