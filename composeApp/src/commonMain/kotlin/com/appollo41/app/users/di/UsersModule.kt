package com.appollo41.app.users.di

import androidx.lifecycle.SavedStateHandle
import com.appollo41.app.users.details.UserDetailsViewModel
import com.appollo41.app.users.list.UserListViewModel
import com.appollo41.app.users.repository.UserRepository
import org.koin.dsl.module

val usersModule = module {

    single {
        UserRepository(database = get(), dispatcherProvider = get())
    }

    factory {
        UserListViewModel(userRepository = get())
    }

    factory { (savedStateHandle: SavedStateHandle) ->
        UserDetailsViewModel(
            savedStateHandle = savedStateHandle,
            userRepository = get(),
        )
    }

    factory<UserDetailsViewModel.Factory> {
        object : UserDetailsViewModel.Factory {
            override fun create(savedStateHandle: SavedStateHandle): UserDetailsViewModel = get()
        }
    }
}