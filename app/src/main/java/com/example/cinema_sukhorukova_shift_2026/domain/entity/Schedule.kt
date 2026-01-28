package com.example.cinema_sukhorukova_shift_2026.domain.entity

data class Schedule(
    val date: String,
    val seances: List<Seance>
)