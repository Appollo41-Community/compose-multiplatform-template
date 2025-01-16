package com.appollo41.app.core.coroutines

import org.koin.dsl.module

val coroutinesModule = module {
    single {
        DispatcherProvider()
    }
}