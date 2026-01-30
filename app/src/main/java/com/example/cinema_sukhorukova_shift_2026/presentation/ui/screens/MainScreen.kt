package com.example.cinema_sukhorukova_shift_2026.presentation.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.NavControllerHolder
import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.NavigationOption
import com.example.cinema_sukhorukova_shift_2026.presentation.ui.components.BottomBar
import org.koin.compose.getKoin
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // передаем navController в holder
    val holder: NavControllerHolder = getKoin().get()
    LaunchedEffect(Unit) {
        holder.navController = navController
    }

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    Scaffold(
        bottomBar = {
            BottomBar(
                currentRoute = currentRoute,
                navController = navController
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = NavigationOption.Afisha.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(NavigationOption.Afisha.route) {
                AfishaScreen()
            }

            composable(NavigationOption.Tickets.route) {
                TicketsScreen()
            }

            composable(NavigationOption.Profile.route) {
                ProfileScreen()
            }

            composable(
                route = NavigationOption.Film.route,
                arguments = listOf(navArgument("filmId") { type = NavType.StringType })
            ) { backStackEntry ->
                val filmId = backStackEntry.arguments?.getString("filmId") ?: return@composable
                FilmScreen(filmId = filmId)
            }
        }
    }
}