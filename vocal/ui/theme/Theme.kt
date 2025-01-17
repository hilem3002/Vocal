package com.example.vocal.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.core.view.WindowCompat
import com.example.vocal.R
import com.example.vocal.viewModels.MainViewModel

private val DarkColorScheme = darkColorScheme(
    background = DarkerBlue,
    onBackground = DarkBlue,
    primaryContainer = Blue,
    secondaryContainer = Green,
    tertiaryContainer = Orange,
    surface = Color.White
)

private val LightColorScheme = lightColorScheme(
    background = DarkWhite,
    onBackground = Color.White,
    primaryContainer = Blue,
    secondaryContainer = Green,
    tertiaryContainer = Orange,
    surface = DarkBlue
)

@Composable
fun VocalTheme(
    mainViewModel : MainViewModel,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme || mainViewModel.isDarkThemeOnState.value -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}