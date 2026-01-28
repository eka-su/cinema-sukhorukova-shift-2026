package com.example.cinema_sukhorukova_shift_2026.data.api

import com.example.cinema_sukhorukova_shift_2026.data.models.dto.FilmDto
import com.example.cinema_sukhorukova_shift_2026.data.models.dto.FilmsResponseDto
import com.example.cinema_sukhorukova_shift_2026.data.models.dto.ScheduleResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CinemaApi {

    @GET("cinema/films")
    suspend fun getTodayFilms(): FilmsResponseDto

    @GET("cinema/film/{filmId}")
    suspend fun getFilm(@Path("filmId") filmId: String): FilmDto

    @GET("cinema/film/{filmId}/schedule")
    suspend fun getSchedule(@Path("filmId") filmId: String): ScheduleResponseDto
}
