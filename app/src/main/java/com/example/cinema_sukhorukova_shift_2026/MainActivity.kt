package com.example.cinema_sukhorukova_shift_2026

import android.R.attr.theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cinema_sukhorukova_shift_2026.presentation.ui.screens.MainScreen
import com.example.cinema_sukhorukova_shift_2026.presentation.ui.theme.CinemaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaTheme()  {
            MainScreen()}
        }
    }
}

