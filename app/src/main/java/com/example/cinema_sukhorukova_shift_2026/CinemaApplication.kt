package com.example.cinema_sukhorukova_shift_2026


import android.app.Application
import com.example.cinema_sukhorukova_shift_2026.di.NavigationModule
import com.example.cinema_sukhorukova_shift_2026.di.NetworkModule
import com.example.cinema_sukhorukova_shift_2026.di.RepositoryModule
import com.example.cinema_sukhorukova_shift_2026.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CinemaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CinemaApplication)
            modules(
                listOf(
                    NetworkModule,
                    RepositoryModule,
                    ViewModelModule,
                    NavigationModule,
                )
            )
        }
    }
}

