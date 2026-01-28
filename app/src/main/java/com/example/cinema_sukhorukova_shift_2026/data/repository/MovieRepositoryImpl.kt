package com.example.cinema_sukhorukova_shift_2026.data.repository

import android.util.Log
import com.example.cinema_sukhorukova_shift_2026.data.api.CinemaApi
import com.example.cinema_sukhorukova_shift_2026.data.models.mapper.MovieMapper
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Movie
import com.example.cinema_sukhorukova_shift_2026.domain.repository.MovieRepository
import com.google.gson.Gson

class MovieRepositoryImpl(private val api: CinemaApi) : MovieRepository {

    override suspend fun getTodayMovies(): List<Movie> {
        val response = api.getTodayFilms()
        return if (response.success) {
            response.films.map { MovieMapper.mapFilmDtoToMovie(it) }
        } else {
            emptyList()
        }
    }

    override suspend fun getMovie(id: String): Movie {
        val response = api.getFilm(id) // FilmsDetailDto
        if (!response.success) {
            throw Exception("Ошибка при получении фильма")
        }
        return MovieMapper.mapFilmDtoToMovie(response.film)
    }
}
