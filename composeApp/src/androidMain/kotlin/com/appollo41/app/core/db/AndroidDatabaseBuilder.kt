package com.appollo41.app.core.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appollo41.app.core.db.AppDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = context.getDatabasePath("app_database.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath,
    )
}
