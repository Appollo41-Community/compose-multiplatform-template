package com.appollo41.app.users.repository

import com.appollo41.app.core.coroutines.DispatcherProvider
import com.appollo41.app.core.db.AppDatabase
import com.appollo41.app.users.db.User
import kotlinx.coroutines.withContext
import kotlin.random.Random

class UserRepository(
    private val database: AppDatabase,
    private val dispatcherProvider: DispatcherProvider,
) {

    fun observeAllUsers() = database.customers().observeAllUsers()

    fun observeUser(id: Long) = database.customers().observeUser(id = id)

    suspend fun insertUser(name: String, email: String) = withContext(dispatcherProvider.io()) {
        database.customers().insertUser(
            user = User(
                name = name,
                email = email,
                sensitiveData = Random.nextInt(from = 1, until = 1_000),
            )
        )
    }

    suspend fun deleteUser(id: Long) {
        withContext(dispatcherProvider.io()) {
            database.customers().deleteUser(id = id)
        }
    }
}