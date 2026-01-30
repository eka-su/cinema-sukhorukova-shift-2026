package com.example.cinema_sukhorukova_shift_2026.di

import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.Impl.GlobalRouterImpl
import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.Impl.MainRouterImpl
import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.NavControllerHolder
import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.api.GlobalRouter
import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.api.MainRouter
import org.koin.dsl.module

val NavigationModule = module {
    single { NavControllerHolder() }
    single<GlobalRouter> { GlobalRouterImpl(get()) }
    single<MainRouter> { MainRouterImpl(get()) }
}
