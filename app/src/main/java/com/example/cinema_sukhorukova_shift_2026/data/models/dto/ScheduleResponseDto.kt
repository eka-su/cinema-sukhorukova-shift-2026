package com.example.cinema_sukhorukova_shift_2026.data.models.dto

data class ScheduleResponseDto(
    val success: Boolean,
    val reason: String?,
    val schedules: List<ScheduleDto> // не обьект а списко
)
