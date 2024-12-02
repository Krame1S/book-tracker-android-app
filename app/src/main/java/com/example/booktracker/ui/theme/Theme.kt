package com.example.booktracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFF121212), // Dark gray
    surface = Color(0xFF1E1E1E), // Slightly lighter gray for surfaces
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color(0xFFE0E0E0), // Light gray for text
    onSurface = Color(0xFFE0E0E0) // Light gray for text
)


private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = BackgroundColorLight,
    surface = SurfaceColorLight,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = TextColorLight,
    onSurface = TextColorLight
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