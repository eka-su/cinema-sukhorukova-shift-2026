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


object MovieMapper {

    fun mapFilmDtoToMovie(filmDto: FilmDto): Movie {
        return Movie(
            id = filmDto.id,
            name = filmDto.name,
            originalName = filmDto.originalName,
            description = filmDto.description,
            releaseDate = filmDto.releaseDate,
            actors = filmDto.actors.map { mapPersonDtoToPerson(it) },
            directors = filmDto.directors.map { mapPersonDtoToPerson(it) },
            runtime = filmDto.runtime,
            ageRating = filmDto.ageRating,
            genres = filmDto.genres,
            ratings = mapRatingsDtoToRatings(filmDto.userRatings),
            img = filmDto.img,
            country = mapCountryDtoToCountry(filmDto.country)
        )
    }

    private fun mapPersonDtoToPerson(personDto: PersonDto): Person {
        return Person(
            id = personDto.id,
            professions = personDto.professions.joinToString(", "),
            fullName = personDto.fullName
        )
    }

    private fun mapRatingsDtoToRatings(ratingsDto: RatingsDto): Ratings {
        return Ratings(
            kinopoisk = ratingsDto.kinopoisk,
            imdb = ratingsDto.imdb
        )
    }

    private fun mapCountryDtoToCountry(countryDto: CountryDto): Country {
        return Country(
            id = countryDto.id,
            name = countryDto.name,
            code = countryDto.code,
            code2 = countryDto.code2
        )
    }

    fun mapScheduleDtoToSchedule(scheduleDto: ScheduleDto): Schedule {
        return Schedule(
            date = scheduleDto.date,
            seances = scheduleDto.seances.map { mapSeanceDtoToSeance(it) }
        )
    }

    private fun mapSeanceDtoToSeance(seanceDto: SeanceDto): Seance {
        return Seance(
            time = seanceDto.time,
            hall = mapHallDtoToHall(seanceDto.hall)
        )
    }

    private fun mapHallDtoToHall(hallDto: HallDto): Hall {
        return Hall(
            name = hallDto.name,
            places = hallDto.places.map { row ->
                row.map { placeDto -> Place(type = placeDto.type, price = placeDto.price) }
            }
        )
    }
}