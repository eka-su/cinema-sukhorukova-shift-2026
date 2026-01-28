package com.example.cinema_sukhorukova_shift_2026.domain.repository

import com.example.cinema_sukhorukova_shift_2026.domain.entity.Schedule

interface ScheduleRepository {
    suspend fun getSchedule(movieId: String): List<Schedule>
}