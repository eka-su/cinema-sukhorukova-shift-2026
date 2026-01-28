package com.example.cinema_sukhorukova_shift_2026.presentation.navigation.api

interface GlobalRouter {

    fun openAfisha()              // открыть экран афиши (список фильмов)

    fun openTickets()             // открыть экран купленных билетов

    fun openProfile()             // открыть экран профиля

    fun openFilm(filmId: String)  // открыть конкретный фильм

    fun back()                    // вернуться назад
}
