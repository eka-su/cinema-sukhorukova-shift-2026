package com.example.cinema_sukhorukova_shift_2026.domain.repository

import com.example.cinema_sukhorukova_shift_2026.domain.entity.Movie

interface MovieRepository {
    suspend fun getTodayMovies(): List<Movie>
    suspend fun getMovie(id: String): Movie
}