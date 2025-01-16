package com.appollo41.app.users.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appollo41.app.navigation.customerIdOrThrow
import com.appollo41.app.users.db.User
import com.appollo41.app.users.details.UserDetailsContract.SideEffect
import com.appollo41.app.users.details.UserDetailsContract.UiEvent
import com.appollo41.app.users.details.UserDetailsContract.UiState
import com.appollo41.app.users.list.model.UserUiModel
import com.appollo41.app.users.repository.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
) : ViewModel() {

    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): UserDetailsViewModel
    }

    private val customerId = savedStateHandle.customerIdOrThrow

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()
    private fun setState(reducer: UiState.() -> UiState) = _state.getAndUpdate(reducer)

    private val events = MutableSharedFlow<UiEvent>()
    fun setEvent(event: UiEvent) = viewModelScope.launch { events.emit(event) }

    private val _effects = Channel<SideEffect>()
    val effects = _effects.receiveAsFlow()
    private suspend fun setEffect(effect: SideEffect) = _effects.send(effect)

    init {
        observeEvents()
        observeUser(id = customerId)
    }

    private fun observeUser(id: Long) = viewModelScope.launch {
        userRepository.observeUser(id)
            // To prevent receiving `null` when deleting user entity
            .filterNotNull()
            .collect {
                setState {
                    copy(
                        customer = it.mapAsUserUiModel(),
                    )
                }
            }
    }

    private fun observeEvents() = viewModelScope.launch {
        events.collect {
            when (it) {
                UiEvent.DeleteCustomer -> deleteCustomer()
            }
        }
    }

    private fun deleteCustomer() = viewModelScope.launch {
        userRepository.deleteUser(id = customerId)
        setEffect(SideEffect.CustomerDeleted)
    }

    private fun User.mapAsUserUiModel() = UserUiModel(
        id = this.id,
        name = this.name,
        email = this.email,
    )

}
