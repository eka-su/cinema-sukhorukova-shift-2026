package com.example.cinema_sukhorukova_shift_2026.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema_sukhorukova_shift_2026.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieListScreen(viewModel: MovieListViewModel = koinViewModel()) {
    val movies by viewModel.movies
    val isLoading by viewModel.isLoading

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    "Фильмы сегодня: ${movies.size}",
                    fontSize = 30.sp
                )
                Spacer(Modifier.height(8.dp))

                movies.forEach { movie ->
                    Text(" ${movie.name}")
                    Text("  id: ${movie.id}")
                    Text("  оригинальное название: ${movie.originalName}")
                    Text("  рейтинг kp: ${movie.ratings.kinopoisk}")
                    Text("  жанры: ${movie.genres.joinToString()}")
                    Text("  страна: ${movie.country.name}")
                    Divider(Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}
