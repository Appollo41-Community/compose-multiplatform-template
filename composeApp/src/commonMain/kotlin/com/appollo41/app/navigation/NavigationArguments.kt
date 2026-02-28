package com.appollo41.app.navigation

import androidx.lifecycle.SavedStateHandle

const val USER_ID = "userId"
inline val SavedStateHandle.userId: Long? get() = get(USER_ID)
inline val SavedStateHandle.userIdOrThrow: Long get() = get(USER_ID)
    ?: throw IllegalStateException("$USER_ID is mandatory and can not be null")
