package com.example.cinema_sukhorukova_shift_2026.domain.usecase

import com.example.cinema_sukhorukova_shift_2026.domain.entity.Movie
import com.example.cinema_sukhorukova_shift_2026.domain.repository.MovieRepository

// Получение фильма по id
class GetMovieByIdUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(id: String): Movie {
        return repository.getMovie(id)
    }
}