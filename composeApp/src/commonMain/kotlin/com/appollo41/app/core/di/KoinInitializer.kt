package com.appollo41.app.core.di

import com.appollo41.app.core.coroutines.coroutinesModule
import com.appollo41.app.core.db.di.databaseModule
import com.appollo41.app.users.di.usersModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            databaseModule(),
            coroutinesModule,
            usersModule,
        )
    }
}