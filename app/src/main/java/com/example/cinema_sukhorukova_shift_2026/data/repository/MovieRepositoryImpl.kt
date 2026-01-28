package com.example.cinema_sukhorukova_shift_2026.data.repository

import com.example.cinema_sukhorukova_shift_2026.data.api.CinemaApi
import com.example.cinema_sukhorukova_shift_2026.data.models.mapper.MovieMapper
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Movie
import com.example.cinema_sukhorukova_shift_2026.domain.repository.MovieRepository

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
        val filmDto = api.getFilm(id)
        return MovieMapper.mapFilmDtoToMovie(filmDto)
    }
}
