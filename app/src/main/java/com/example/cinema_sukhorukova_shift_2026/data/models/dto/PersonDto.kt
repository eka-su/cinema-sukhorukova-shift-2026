package com.example.cinema_sukhorukova_shift_2026.data.models.dto

data class PersonDto(
    val id: String,
    val professions: List<String>, // не обьект, список
    val fullName: String
)