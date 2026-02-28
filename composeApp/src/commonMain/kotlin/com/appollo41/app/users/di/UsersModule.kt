package com.appollo41.app.users.di

import com.appollo41.app.users.details.UserDetailsViewModel
import com.appollo41.app.users.list.UserListViewModel
import com.appollo41.app.users.repository.UserRepository
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val usersModule = module {

    single {
        UserRepository(database = get(), dispatcherProvider = get())
    }

    viewModelOf(::UserListViewModel)

    viewModelOf(::UserDetailsViewModel)
}