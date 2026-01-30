package com.example.cinema_sukhorukova_shift_2026.domain.usecase

import com.example.cinema_sukhorukova_shift_2026.domain.entity.Movie
import com.example.cinema_sukhorukova_shift_2026.domain.repository.MovieRepository

// Получение всех фильмов сегодня
class GetMovieByIdUseCase(
    repository: MovieRepository
) : suspend (String) -> Movie by repository::getMovie
