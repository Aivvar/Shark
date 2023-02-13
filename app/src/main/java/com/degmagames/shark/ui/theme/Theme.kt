package com.degmagames.shark.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = White,
    secondary = Green,
    secondaryVariant = Green2,
    background = Black,
    onBackground = Dark,
    surface = Blue2
)

private val LightColorPalette = lightColors(
    primary = Dark,
    secondary = Green,
    secondaryVariant = Green2,
    background = Gray,
    onBackground = White,
    surface = Blue2
)

@Composable
fun SharkTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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