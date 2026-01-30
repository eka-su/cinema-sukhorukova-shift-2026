package com.example.cinema_sukhorukova_shift_2026.presentation.ui.screens

import com.example.cinema_sukhorukova_shift_2026.domain.entity.Movie
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Schedule

sealed class FilmScreenState {
    data object Initial : FilmScreenState()
    data object Loading : FilmScreenState()
    data class Content(
        val movie: Movie,
        val schedule: List<Schedule>
    ) : FilmScreenState()
    data class Error(val message: String) : FilmScreenState()
}
