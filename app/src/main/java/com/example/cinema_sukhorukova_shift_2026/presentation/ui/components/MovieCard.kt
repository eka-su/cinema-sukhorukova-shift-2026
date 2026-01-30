package com.example.cinema_sukhorukova_shift_2026.presentation.ui.components

import androidx.compose.foundation.background
import com.example.cinema_sukhorukova_shift_2026.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Country
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Movie
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Person
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Ratings
@Composable
fun MovieCard(
    movie: Movie,
    showDetailsButton: Boolean,
    onDetailsClick: (() -> Unit)? = null
) {
    // Цвета берём из темы
    val cardBackgroundColor = MaterialTheme.colorScheme.surface
    val cardTextColor = MaterialTheme.colorScheme.onSurface
    val cardSubTextColor = MaterialTheme.colorScheme.onSurfaceVariant

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
    ) {
        Column {
            // Верхняя картинка
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                AsyncImage(
                    model = movie.img,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                // Информационный блок жанр/страна/год
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .height(48.dp)
                        .widthIn(min = 80.dp, max = 150.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = movie.genres.firstOrNull() ?: "",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = cardTextColor
                    )

                    val year = remember(movie.releaseDate) {
                        movie.releaseDate.takeLast(4).filter { it.isDigit() }
                    }

                    Text(
                        text = "${movie.country.name}, $year",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = cardSubTextColor
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            // Нижняя часть карточки с названием, описанием и кнопкой
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "${movie.name} (${movie.ageRating})",
                    style = MaterialTheme.typography.titleMedium,
                    color = cardTextColor
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = movie.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = cardTextColor,
                    maxLines = if (showDetailsButton) 3 else Int.MAX_VALUE,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(8.dp))

                RatingStarsCanvas(rating = movie.ratings.kinopoisk.toDoubleOrNull() ?: 0.0)

                if (showDetailsButton) {
                    Spacer(Modifier.height(12.dp))
                    PrimaryButton(
                        text = stringResource(R.string.details_button),
                        onClick = { onDetailsClick?.invoke() },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MovieCardPreview_Afisha() {
    MovieCard(
        movie = previewMovie,
        showDetailsButton = true,
        onDetailsClick = {}
    )
}


private val previewMovie = Movie(
    id = "1",
    name = "Интерстеллар",
    originalName = "Interstellar",
    description = "Когда Земля становится непригодной для жизни, группа исследователей отправляется сквозь червоточину в поисках новой планеты для человечества.",
    releaseDate = "2222",
    actors = listOf(
        Person(id = "1", professions = "Actor", fullName = "Мэттью МакКонахи"),
        Person(id = "2", professions = "Actor", fullName = "Энн Хэтэуэй")
    ),
    directors = listOf(
        Person(id = "3", professions = "Director", fullName = "Кристофер Нолан")
    ),
    runtime = 169,
    ageRating = "R", // рейтинг для скобок
    genres = listOf("Фантастика", "Драма"),
    ratings = Ratings(kinopoisk = "8.6", imdb = "8.7"),
    img = "https://img.freepik.com/premium-photo/red-cat-isolated-white-background_333900-3173.jpg?semt=ais_hybrid&w=740&q=80",
    country = Country(id = 1, name = "США", code = "US", code2 = "USA")
)
