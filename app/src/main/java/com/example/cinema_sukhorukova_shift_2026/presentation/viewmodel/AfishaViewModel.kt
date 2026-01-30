package com.example.cinema_sukhorukova_shift_2026.presentation.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_sukhorukova_shift_2026.domain.usecase.GetTodayMoviesUseCase
import com.example.cinema_sukhorukova_shift_2026.presentation.ui.screens.AfishaScreenState
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.api.MainRouter



class AfishaViewModel(
    private val getTodayMovies: GetTodayMoviesUseCase,
    private val router: MainRouter
) : ViewModel() {

    private val _state = mutableStateOf<AfishaScreenState>(AfishaScreenState.Initial)
    val state: State<AfishaScreenState> = _state

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _state.value = AfishaScreenState.Loading
            runCatching { getTodayMovies() }
                .onSuccess {
                    _state.value = AfishaScreenState.Content(it)
                }
                .onFailure {
                    _state.value = AfishaScreenState.Error(it.message ?: "Ошибка")
                }
        }
    }

    fun onMovieClick(id: String) {
        router.openFilm(id)
    }
}
