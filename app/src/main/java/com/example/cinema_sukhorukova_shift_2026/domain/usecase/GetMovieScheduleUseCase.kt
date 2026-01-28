package com.example.cinema_sukhorukova_shift_2026.domain.usecase

import com.example.cinema_sukhorukova_shift_2026.domain.entity.Schedule
import com.example.cinema_sukhorukova_shift_2026.domain.repository.ScheduleRepository


// Получение расписания фильма
class GetMovieScheduleUseCase(
    repository: ScheduleRepository
) : suspend (String) -> List<Schedule> by repository::getSchedule