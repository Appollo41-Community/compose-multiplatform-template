package com.appollo41.app.users.details

import com.appollo41.app.users.list.model.UserUiModel

interface UserDetailsContract {

    data class UiState(
        val customer: UserUiModel? = null,
    )

    sealed class UiEvent {
        data object DeleteCustomer : UiEvent()
    }

    sealed class SideEffect {
        data object CustomerDeleted : SideEffect()
    }

}