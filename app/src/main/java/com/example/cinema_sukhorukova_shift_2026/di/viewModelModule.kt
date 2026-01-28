package com.example.cinema_sukhorukova_shift_2026.di

import com.example.cinema_sukhorukova_shift_2026.presentation.viewmodel.MovieListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieListViewModel(get()) }
}


