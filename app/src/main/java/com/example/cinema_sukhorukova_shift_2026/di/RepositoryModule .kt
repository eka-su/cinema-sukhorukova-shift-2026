package com.example.cinema_sukhorukova_shift_2026.di

import com.example.cinema_sukhorukova_shift_2026.data.repository.MovieRepositoryImpl
import com.example.cinema_sukhorukova_shift_2026.data.repository.ScheduleRepositoryImpl
import com.example.cinema_sukhorukova_shift_2026.domain.repository.MovieRepository
import com.example.cinema_sukhorukova_shift_2026.domain.repository.ScheduleRepository
import com.example.cinema_sukhorukova_shift_2026.domain.usecase.GetMovieByIdUseCase
import com.example.cinema_sukhorukova_shift_2026.domain.usecase.GetMovieScheduleUseCase
import com.example.cinema_sukhorukova_shift_2026.domain.usecase.GetTodayMoviesUseCase
import org.koin.dsl.module

val RepositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<ScheduleRepository> { ScheduleRepositoryImpl(get()) }
    single { GetTodayMoviesUseCase(get()) }
    single { GetMovieByIdUseCase(get()) }
    single { GetMovieScheduleUseCase(get()) }

}
