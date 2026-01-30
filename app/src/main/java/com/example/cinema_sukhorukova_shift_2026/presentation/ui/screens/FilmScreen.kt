package com.example.cinema_sukhorukova_shift_2026.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cinema_sukhorukova_shift_2026.R
import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.NavControllerHolder
import com.example.cinema_sukhorukova_shift_2026.presentation.ui.components.MovieCard
import com.example.cinema_sukhorukova_shift_2026.presentation.viewmodel.FilmViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.getKoin

@Composable
fun FilmScreen(
    filmId: String,
    viewModel: FilmViewModel = koinViewModel()
) {
    val state by viewModel.state
    val backgroundColor = MaterialTheme.colorScheme.background

    val navController = getKoin().get<NavControllerHolder>().navController

    LaunchedEffect(filmId) {
        viewModel.loadFilm(filmId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 16.dp)
    ) {
        when (state) {
            FilmScreenState.Initial,
            FilmScreenState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is FilmScreenState.Content -> {
                val movie = (state as FilmScreenState.Content).movie

                Column(modifier = Modifier.fillMaxSize()) {
                    // Заголовок "О фильме" с иконкой назад
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Назад",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    navController?.popBackStack()
                                }
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = stringResource(R.string.film_about_title),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    // Карточка фильма под заголовком
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        item {
                            MovieCard(
                                movie = movie,
                                showDetailsButton = false // убираем кнопку
                            )
                        }
                    }
                }
            }

            is FilmScreenState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = (state as FilmScreenState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
