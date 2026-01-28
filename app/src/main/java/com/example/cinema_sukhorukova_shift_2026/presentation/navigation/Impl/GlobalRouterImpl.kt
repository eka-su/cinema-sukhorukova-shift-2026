package com.example.cinema_sukhorukova_shift_2026.presentation.navigation.Impl

import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.NavControllerHolder
import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.NavigationOption
import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.api.GlobalRouter

class GlobalRouterImpl(
    private val holder: NavControllerHolder
) : GlobalRouter {

    override fun openAfisha() {
        holder.navController?.navigate(NavigationOption.Afisha.route)
    }

    override fun openTickets() {
        holder.navController?.navigate(NavigationOption.Tickets.route)
    }

    override fun openProfile() {
        holder.navController?.navigate(NavigationOption.Profile.route)
    }

    override fun openFilm(filmId: String) {
        holder.navController?.navigate(
            NavigationOption.Film.create(filmId)
        )
    }

    override fun back() {
        holder.navController?.popBackStack()
    }
}
