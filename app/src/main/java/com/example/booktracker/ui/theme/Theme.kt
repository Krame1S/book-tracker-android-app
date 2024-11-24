package com.example.booktracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = SlateGray,
    secondary = MediumGray,
    background = DarkBackground, // Новый цвет для фона
    surface = MediumGray,
    onPrimary = OffWhite,
    onSecondary = OffWhite,
    onBackground = OffWhite,
    onSurface = OffWhite
)

private val LightColorScheme = lightColorScheme(
    primary = SlateGray,
    secondary = MediumGray,
    background = LightBackground, // Новый цвет для фона
    surface = LightSurface, // Новый цвет для поверхности коллекций
    onPrimary = OffWhite,
    onSecondary = OffWhite,
    onBackground = SlateGray,
    onSurface = SlateGray
)


@Composable
fun BookTrackerTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
