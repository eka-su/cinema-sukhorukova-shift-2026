package com.example.cinema_sukhorukova_shift_2026.di

import com.example.cinema_sukhorukova_shift_2026.data.repository.MovieRepository1
import com.example.cinema_sukhorukova_shift_2026.data.repository.ScheduleRepository1
import com.example.cinema_sukhorukova_shift_2026.domain.repository.MovieRepository
import com.example.cinema_sukhorukova_shift_2026.domain.repository.ScheduleRepository
import com.example.cinema_sukhorukova_shift_2026.domain.usecase.GetMovieByIdUseCase
import com.example.cinema_sukhorukova_shift_2026.domain.usecase.GetMovieScheduleUseCase
import com.example.cinema_sukhorukova_shift_2026.domain.usecase.GetTodayMoviesUseCase
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepository1(get()) }
    single<ScheduleRepository> { ScheduleRepository1(get()) }
    single { GetTodayMoviesUseCase(get()) }
    single { GetMovieByIdUseCase(get()) }
    single { GetMovieScheduleUseCase(get()) }

}
