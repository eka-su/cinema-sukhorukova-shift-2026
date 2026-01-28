package com.example.cinema_sukhorukova_shift_2026.domain.entity

data class Movie(
    val id: String,
    val name: String,
    val originalName: String,
    val description: String,
    val releaseDate: String,
    val actors: List<Person>,
    val directors: List<Person>,
    val runtime: Int,
    val ageRating: String,
    val genres: List<String>,
    val ratings: Ratings,
    val img: String,
    val country: Country
)