package com.example.cinema_sukhorukova_shift_2026.domain.entity

data class Hall(
    val name: String,
    val places: List<List<Place>> // двумерный список мест
)