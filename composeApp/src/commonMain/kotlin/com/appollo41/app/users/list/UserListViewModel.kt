package com.appollo41.app.users.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appollo41.app.users.db.User
import com.appollo41.app.users.list.UserListContract.UiEvent
import com.appollo41.app.users.list.UserListContract.UiState
import com.appollo41.app.users.list.model.UserUiModel
import com.appollo41.app.users.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class UserListViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()
    private fun setState(reducer: UiState.() -> UiState) = _state.getAndUpdate(reducer)

    private val events = MutableSharedFlow<UiEvent>()
    fun setEvent(event: UiEvent) = viewModelScope.launch { events.emit(event) }

    init {
        observeEvents()
        observeAllUsers()
    }

    private fun observeAllUsers() = viewModelScope.launch {
        userRepository.observeAllUsers().collect {
            setState {
                copy(
                    customers = it.mapAsUserUiModel(),
                )
            }
        }
    }

    private fun observeEvents() = viewModelScope.launch {
        events.collect {
            when (it) {
                UiEvent.GenerateRandomUser -> generateRandomUser()
            }
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun generateRandomUser() = viewModelScope.launch {
        userRepository.insertUser(
            name = Uuid.random().toString(),
            email = "developer@appollo41.com",
        )
    }

    private fun List<User>.mapAsUserUiModel() = this.map {
        UserUiModel(id = it.id, name = it.name, email = it.email)
    }

}
