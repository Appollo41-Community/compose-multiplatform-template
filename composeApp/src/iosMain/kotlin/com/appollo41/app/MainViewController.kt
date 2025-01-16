package com.appollo41.app

import androidx.compose.ui.window.ComposeUIViewController
import com.appollo41.app.core.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
        Napier.base(DebugAntilog())
    }
) { App() }