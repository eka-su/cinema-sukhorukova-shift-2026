package com.example.cinema_sukhorukova_shift_2026.data.models.dto

data class FilmsResponseDto(
    val success: Boolean,
    val reason: String?,
    val films: List<FilmDto>
)