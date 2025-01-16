package com.appollo41.app.users.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User")
    fun observeAllUsers() : Flow<List<User>>

    @Query("SELECT * FROM User WHERE id = :id")
    fun observeUser(id: Long) : Flow<User?>

    @Query("DELETE FROM User WHERE id = :id")
    suspend fun deleteUser(id: Long)
}