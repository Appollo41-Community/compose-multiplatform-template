package com.appollo41.app

import androidx.compose.runtime.Composable
import com.appollo41.app.navigation.AppNavigation
import com.appollo41.app.theme.AppTheme
import com.appollo41.app.theme.PlatformEdgeToEdge

@Composable
fun App() {
    AppTheme {
        PlatformEdgeToEdge()
        AppNavigation()
    }
}