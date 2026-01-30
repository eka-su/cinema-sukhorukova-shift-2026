package com.example.cinema_sukhorukova_shift_2026.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import com.example.cinema_sukhorukova_shift_2026.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema_sukhorukova_shift_2026.presentation.ui.theme.StarEmpty
import com.example.cinema_sukhorukova_shift_2026.presentation.ui.theme.StarFilled
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RatingStarsCanvas(
    rating: Double,
    modifier: Modifier = Modifier,
    starCount: Int = 5,
    starSize: Int = 20, // видимая звезда 20dp
    starContainerSize: Int = 24, // контейнер 24x24dp
    starSpacing: Int = 4,
    emptyStarColor: Color = StarEmpty,
    filledStarColor: Color = StarFilled,
    maxRating: Double = 10.0
) {
    val starSizeDp = starSize.dp
    val containerSizeDp = starContainerSize.dp
    val spacingDp = starSpacing.dp

    // масштабируем рейтинг из 10-балльной системы в 5-звездную
    val scaledRating = (rating / maxRating) * starCount

    // Располагаем звезды в ряд
    Row(modifier = modifier) {
        repeat(starCount) { starIndex ->
            // Вычисляем заполнение на основе рейтинга
            val fillPercentage = when {
                scaledRating >= starIndex + 1 -> 1f // полная звезда
                scaledRating > starIndex && scaledRating < starIndex + 1 ->
                    (scaledRating - starIndex).toFloat() // частично
                else -> 0f // пустая
            }

            // Контейнер звезды, чтобы все они одинакового размера
            Box(
                modifier = Modifier
                    .size(containerSizeDp) // контейнер 24x24
                    .padding(end = if (starIndex < starCount - 1) spacingDp else 0.dp),
                contentAlignment = Alignment.Center
            ) {
                // 1 звезда
                Canvas(modifier = Modifier.size(starSizeDp)) { // видимая звезда 20x20
                    val starRadius = size.minDimension / 2
                    val starCenter = Offset(size.width / 2, size.height / 2)

                    // Рисуем серую звезду как фон
                    drawFilledStar(center = starCenter, radius = starRadius, color = emptyStarColor)

                    // Рисуем желтую часть поверх, если есть заполнение
                    if (fillPercentage > 0f) {
                        val fillWidth = size.width * fillPercentage
                        withTransform({
                            clipRect(left = 0f, top = 0f, right = fillWidth, bottom = size.height)
                        }) {
                            drawFilledStar(center = starCenter, radius = starRadius, color = filledStarColor)
                        }
                    }
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = stringResource(R.string.kinopoisk_label, rating.toString()), // Показываем оригинальный рейтинг (до 10.0)
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.onBackground      // стиль лучше
    )
}


// Рисует закрашенную звезду
private fun DrawScope.drawFilledStar(
    center: Offset,
    radius: Float,
    color: Color
) {
    // Создаем форму звезды и закрашиваем ее
    val starPath = createStarPath(center, radius)
    drawPath(path = starPath, color = color)
}

// Создает геометрическую форму звезды с 5 лучами
private fun createStarPath(
    center: Offset,
    outerRadius: Float
): Path {
    return Path().apply {
        val innerRadius = outerRadius * 0.4f
        val numberOfPoints = 5

        // Звезда состоит из 10 точек: 5 внешних + 5 внутренних
        for (pointIndex in 0 until numberOfPoints * 2) {
            // Четные индексы = внешние точки, нечетные = внутренние
            val isOuterPoint = pointIndex % 2 == 0
            val currentRadius = if (isOuterPoint) outerRadius else innerRadius

            // Вычисляем угол для каждой точки
            val angle = PI / numberOfPoints * pointIndex - PI / 2
            val x = center.x + currentRadius * cos(angle).toFloat()
            val y = center.y + currentRadius * sin(angle).toFloat()

            // Соединяем точки
            if (pointIndex == 0) {
                moveTo(x, y) // Начинаем с первой точки
            } else {
                lineTo(x, y) // Проводим линию к следующей точке
            }
        }
        close() // Замыкаем фигуру
    }
}

@Preview(showBackground = true)
@Composable
fun RatingStarsCanvasPreview() {
    Column(modifier = Modifier.padding(16.dp)) {

        RatingStarsCanvas(rating = 10.0)

        Spacer(modifier = Modifier.height(8.dp))

        RatingStarsCanvas(rating = 9.3)

        Spacer(modifier = Modifier.height(8.dp))

        RatingStarsCanvas(rating = 7.5)

        Spacer(modifier = Modifier.height(8.dp))

        RatingStarsCanvas(rating = 5.4)

        Spacer(modifier = Modifier.height(8.dp))

        RatingStarsCanvas(rating = 4.64)
    }
}