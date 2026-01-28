package com.example.cinema_sukhorukova_shift_2026.presentation.navigation.Impl

import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.api.GlobalRouter
import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.api.MainRouter

class MainRouterImpl(
    private val globalRouter: GlobalRouter
) : MainRouter {

    override fun openFilm(filmId: String) {
        globalRouter.openFilm(filmId)
    }
}
