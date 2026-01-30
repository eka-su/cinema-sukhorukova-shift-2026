package com.example.cinema_sukhorukova_shift_2026.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cinema_sukhorukova_shift_2026.R
import org.koin.androidx.compose.koinViewModel
import com.example.cinema_sukhorukova_shift_2026.presentation.ui.components.MovieCard
import com.example.cinema_sukhorukova_shift_2026.presentation.viewmodel.AfishaViewModel


@Composable
fun AfishaScreen(
    viewModel: AfishaViewModel = koinViewModel()
) {
    val state by viewModel.state
    val backgroundColor = MaterialTheme.colorScheme.background

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 16.dp)
    ) {
        Column {
            //Заголовок с иконкой
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_left),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(40.dp))

                Text(
                    text = stringResource(R.string.afisha_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            when (state) {
                AfishaScreenState.Initial,
                AfishaScreenState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is AfishaScreenState.Content -> {
                    val movies = (state as AfishaScreenState.Content).movies

                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        items(movies) { movie ->
                            MovieCard(
                                movie = movie,
                                showDetailsButton = true,
                                onDetailsClick = {
                                    viewModel.onMovieClick(movie.id)
                                }
                            )
                        }
                    }
                }

                is AfishaScreenState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.loading_error),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}