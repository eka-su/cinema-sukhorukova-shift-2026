package com.example.cinema_sukhorukova_shift_2026.presentation.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Movie
import com.example.cinema_sukhorukova_shift_2026.domain.usecase.GetTodayMoviesUseCase
import kotlinx.coroutines.launch


class MovieListViewModel(
    private val getTodayMoviesUseCase: GetTodayMoviesUseCase
) : ViewModel() {

    var movies = mutableStateOf<List<Movie>>(emptyList())
        private set

    var isLoading = mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            isLoading.value = true
            movies.value = getTodayMoviesUseCase()
            isLoading.value = false
        }
    }
}

