package com.example.cinema_sukhorukova_shift_2026.data.models.mapper


import com.example.cinema_sukhorukova_shift_2026.data.models.dto.CountryDto
import com.example.cinema_sukhorukova_shift_2026.data.models.dto.FilmDto
import com.example.cinema_sukhorukova_shift_2026.data.models.dto.HallDto
import com.example.cinema_sukhorukova_shift_2026.data.models.dto.PersonDto
import com.example.cinema_sukhorukova_shift_2026.data.models.dto.RatingsDto
import com.example.cinema_sukhorukova_shift_2026.data.models.dto.ScheduleDto
import com.example.cinema_sukhorukova_shift_2026.data.models.dto.SeanceDto
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Country
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Hall
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Movie
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Person
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Place
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Ratings
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Schedule
import com.example.cinema_sukhorukova_shift_2026.domain.entity.Seance

fun FilmDto.toMovie(): Movie = Movie(
    id = id,
    name = name,
    originalName = originalName,
    description = description,
    releaseDate = releaseDate,
    actors = actors.map { it.toPerson() },
    directors = directors.map { it.toPerson() },
    runtime = runtime,
    ageRating = ageRating,
    genres = genres,
    ratings = userRatings.toRatings(),
    img = img,
    country = country.toCountry()
)

fun PersonDto.toPerson(): Person = Person(
    id = id,
    professions = professions.joinToString(", "),
    fullName = fullName
)

fun RatingsDto.toRatings(): Ratings = Ratings(
    kinopoisk = kinopoisk,
    imdb = imdb
)

fun CountryDto.toCountry(): Country = Country(
    id = id,
    name = name,
    code = code,
    code2 = code2
)

fun ScheduleDto.toSchedule(): Schedule = Schedule(
    date = date,
    seances = seances.map { it.toSeance() }
)

fun SeanceDto.toSeance(): Seance = Seance(
    time = time,
    hall = hall.toHall()
)

fun HallDto.toHall(): Hall = Hall(
    name = name,
    places = places.map { row ->
        row.map { Place(type = it.type, price = it.price) }
    }
)