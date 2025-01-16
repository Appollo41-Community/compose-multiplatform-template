package com.appollo41.app.core.db.di

import com.appollo41.app.core.db.AppDatabase
import com.appollo41.app.core.db.buildAppDatabase
import com.appollo41.app.core.db.getDatabaseBuilder
import org.koin.dsl.module

actual fun databaseModule() = module {
    single<AppDatabase> {
        buildAppDatabase(
            builder = getDatabaseBuilder(
                context = get(),
            )
        )
    }
}