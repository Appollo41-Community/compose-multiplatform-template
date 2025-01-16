package com.appollo41.app

import androidx.compose.runtime.Composable
import com.appollo41.app.navigation.AppNavigation
import com.appollo41.app.theme.AppTheme
import com.appollo41.app.theme.PlatformEdgeToEdge
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        AppTheme {
            PlatformEdgeToEdge()
            AppNavigation()
        }
    }
}