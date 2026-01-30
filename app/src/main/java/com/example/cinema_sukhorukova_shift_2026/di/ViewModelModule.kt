package com.example.cinema_sukhorukova_shift_2026.di


import com.example.cinema_sukhorukova_shift_2026.presentation.viewmodel.AfishaViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { AfishaViewModel(get(), get()) }
}


