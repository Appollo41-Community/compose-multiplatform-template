package com.appollo41.app.users.details

import com.appollo41.app.users.list.model.UserUiModel

interface UserDetailsContract {

    data class UiState(
        val user: UserUiModel? = null,
    )

    sealed class UiEvent {
        data object DeleteUser : UiEvent()
    }

    sealed class SideEffect {
        data object UserDeleted : SideEffect()
    }

}