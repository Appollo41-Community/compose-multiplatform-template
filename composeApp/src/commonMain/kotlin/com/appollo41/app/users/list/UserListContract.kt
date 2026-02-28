package com.appollo41.app.users.list

import com.appollo41.app.users.list.model.UserUiModel

interface UserListContract {

    data class UiState(
        val users: List<UserUiModel> = emptyList(),
    )

    sealed class UiEvent {
        data object GenerateRandomUser : UiEvent()
    }

}