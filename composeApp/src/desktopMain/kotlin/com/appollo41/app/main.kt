package com.appollo41.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.appollo41.app.core.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun main() = application {
    initKoin()
    Napier.base(DebugAntilog())

    Window(
        onCloseRequest = ::exitApplication,
        title = "App",
    ) {
        App()
    }
}