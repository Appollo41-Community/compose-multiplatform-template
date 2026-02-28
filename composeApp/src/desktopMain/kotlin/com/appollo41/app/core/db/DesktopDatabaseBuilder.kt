package com.appollo41.app.core.db

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appDir = File(System.getProperty("user.home"), ".appollo41")
    if (!appDir.exists()) appDir.mkdirs()
    val dbFile = File(appDir, "app_database.db")
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    )
}