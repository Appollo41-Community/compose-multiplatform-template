package com.appollo41.app.theme

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun PlatformEdgeToEdge(statusBarColor: Color?, navBarColor: Color?) {
    val context = LocalContext.current
    if (context is ComponentActivity) {
        if (statusBarColor != null) {
            context.applyEdgeToEdge(
                statusBarColor = statusBarColor,
                isDarkTheme = false,
            )
        }
    }
}

private fun ComponentActivity.applyEdgeToEdge(
    statusBarColor: Color = Color.Transparent,
    navigationBarColor: Color = Color.Transparent,
    isDarkTheme: Boolean,
) {
    enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.auto(
            lightScrim = statusBarColor.toArgb(),
            darkScrim = statusBarColor.toArgb(),
            detectDarkMode = { isDarkTheme },
        ),
        navigationBarStyle = SystemBarStyle.auto(
            lightScrim = navigationBarColor.toArgb(),
            darkScrim = navigationBarColor.toArgb(),
            detectDarkMode = { isDarkTheme },
        ),
    )
}
