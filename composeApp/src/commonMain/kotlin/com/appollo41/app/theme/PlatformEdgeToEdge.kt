package com.appollo41.app.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.appollo41.app.theme.AppTheme

@Composable
expect fun PlatformEdgeToEdge(
    statusBarColor: Color? = AppTheme.colorScheme.surface,
    navBarColor: Color? = AppTheme.colorScheme.surface,
)