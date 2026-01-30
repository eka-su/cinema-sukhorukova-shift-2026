package com.example.cinema_sukhorukova_shift_2026.domain.usecase

import com.example.cinema_sukhorukova_shift_2026.domain.entity.Movie
import com.example.cinema_sukhorukova_shift_2026.domain.repository.MovieRepository

// Получение фильма по id
class GetTodayMoviesUseCase(
    repository: MovieRepository
) : suspend () -> List<Movie> by repository::getTodayMovies