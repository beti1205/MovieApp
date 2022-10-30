package com.beti1205.movieapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.Black,
    primaryVariant = DarkGrey,
    secondary = Orange,
    secondaryVariant = SecondOrange,
    onPrimary = Color.White,
    onSecondary = Color.White
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Mercury,
    secondary = Orange,
    secondaryVariant = SecondOrange,
    onPrimary = Color.Black,
    onSecondary = Color.White
)

@Composable
fun MovieAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
