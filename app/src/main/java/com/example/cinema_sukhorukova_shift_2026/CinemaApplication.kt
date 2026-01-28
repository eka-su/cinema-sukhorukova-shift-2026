package com.example.cinema_sukhorukova_shift_2026


import android.app.Application
import com.example.cinema_sukhorukova_shift_2026.di.networkModule
import com.example.cinema_sukhorukova_shift_2026.di.repositoryModule
import com.example.cinema_sukhorukova_shift_2026.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CinemaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CinemaApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                )
            )
        }
    }
}

