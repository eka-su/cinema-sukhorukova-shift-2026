package com.example.cinema_sukhorukova_shift_2026.di

import com.example.cinema_sukhorukova_shift_2026.data.api.CinemaApi
import com.example.cinema_sukhorukova_shift_2026.setSslSocketFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {

    single {
        OkHttpClient.Builder()
            .setSslSocketFactory()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://shift-intensive.ru/api/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CinemaApi::class.java)
    }
}