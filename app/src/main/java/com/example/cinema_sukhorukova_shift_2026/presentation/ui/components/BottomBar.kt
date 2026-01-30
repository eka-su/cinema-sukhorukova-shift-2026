package com.example.cinema_sukhorukova_shift_2026.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cinema_sukhorukova_shift_2026.R
import com.example.cinema_sukhorukova_shift_2026.presentation.navigation.NavigationOption


@Composable
fun BottomBar(
    currentRoute: String?,
    navController: NavHostController
) {
    val items = listOf(
        NavigationOption.Afisha,
        NavigationOption.Tickets,
        NavigationOption.Profile
    )

    val backgroundColor = MaterialTheme.colorScheme.surface
    val topDividerColor = MaterialTheme.colorScheme.outline

    Column {
        Divider(color = topDividerColor, thickness = 1.dp)

        NavigationBar(
            containerColor = backgroundColor,
            tonalElevation = 0.dp
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(
                                when (item) {
                                    NavigationOption.Afisha -> R.drawable.ic_movie
                                    NavigationOption.Tickets -> R.drawable.ic_ticket
                                    NavigationOption.Profile -> R.drawable.ic_user
                                    else -> R.drawable.ic_movie
                                }
                            ),
                            contentDescription = item.route
                        )
                    },
                    label = {
                        Text(
                            text = item.route.replaceFirstChar { it.uppercase() },
                            fontSize = 10.sp
                        )
                    },
                    selected = currentRoute == item.route,
                    onClick = {
                        if (currentRoute != item.route) {
                            // Если нажали на Афишу, возвращаемся на существующий экран
                            if (item == NavigationOption.Afisha) {
                                navController.popBackStack(
                                    route = NavigationOption.Afisha.route,
                                    inclusive = false
                                )
                            } else {
                                // Для остальных экранов обычная навигация
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true, name = "Tickets selected")
@Composable
fun BottomBarPreviewTickets() {
    MaterialTheme {
        val navController = rememberNavController()
        BottomBar(
            currentRoute = NavigationOption.Tickets.route,
            // currentRoute = null,
            // currentRoute = NavigationOption.Profile.route,
            //currentRoute = NavigationOption.Afisha.route,
            navController = navController
        )
    }
}