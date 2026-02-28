package com.appollo41.app

import android.app.Application
import com.appollo41.app.core.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@AndroidApp)
        }

        Napier.base(DebugAntilog())
    }
}