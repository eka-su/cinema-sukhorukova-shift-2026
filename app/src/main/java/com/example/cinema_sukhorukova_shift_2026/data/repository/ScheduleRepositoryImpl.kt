package com.example.cinema_sukhorukova_shift_2026.data.repository

import com.example.cinema_sukhorukova_shift_2026.data.api.CinemaApi
import com.example.cinema_sukhorukova_shift_2026.data.models.mapper.MovieMapper
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Schedule
import com.example.cinema_sukhorukova_shift_2026.domain.repository.ScheduleRepository

class ScheduleRepositoryImpl(private val api: CinemaApi) : ScheduleRepository {
    override suspend fun getSchedule(movieId: String): List<Schedule> {
        val response = api.getSchedule(movieId)
        return if (response.success) {
            response.schedules.map { MovieMapper.mapScheduleDtoToSchedule(it) }
        } else {
            emptyList()
        }
    }
}
