package com.example.cinema_sukhorukova_shift_2026.presentation.navigation

sealed class NavigationOption(val route: String) {

    object Afisha : NavigationOption("afisha") // список фильмов

    object Tickets : NavigationOption("tickets") // купленные билеты

    object Profile : NavigationOption("profile") // профиль пользователя

    object Film : NavigationOption("film/{filmId}") { // экран конкретного фильма
        fun create(filmId: String) = "film/$filmId"
    }
}
