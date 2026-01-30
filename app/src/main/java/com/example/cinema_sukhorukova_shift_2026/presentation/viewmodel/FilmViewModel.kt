package com.example.cinema_sukhorukova_shift_2026.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.cinema_sukhorukova_shift_2026.domain.usecase.GetMovieByIdUseCase
import com.example.cinema_sukhorukova_shift_2026.presentation.ui.screens.FilmScreenState

class FilmViewModel(
    private val getFilmById: GetMovieByIdUseCase
) : ViewModel() {

    private val _state = mutableStateOf<FilmScreenState>(FilmScreenState.Initial)
    val state: State<FilmScreenState> = _state

    fun loadFilm(filmId: String) {
        viewModelScope.launch {
            _state.value = FilmScreenState.Loading
            runCatching { getFilmById(filmId) }
                .onSuccess { movie ->
                    _state.value = FilmScreenState.Content(
                        movie = movie,
                        schedule = emptyList() // пока расписания нет
                    )
                }
                .onFailure { e ->
                    _state.value = FilmScreenState.Error(e.message ?: "Ошибка загрузки фильма")
                }
        }
    }
}
