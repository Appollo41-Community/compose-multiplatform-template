package com.appollo41.app.users.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appollo41.app.core.compose.AppBarIcon
import com.appollo41.app.resources.Res
import com.appollo41.app.resources.user_details_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserDetailsScreen(
    viewModel: UserDetailsViewModel,
    onClose: () -> Unit,
) {

    LaunchedEffect(viewModel, onClose) {
        viewModel.effects.collect {
            when (it) {
                UserDetailsContract.SideEffect.CustomerDeleted -> onClose()
            }
        }
    }

    val state by viewModel.state.collectAsState()
    UserDetailsScreen(
        state = state,
        eventPublisher = viewModel::setEvent,
        onClose = onClose,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserDetailsScreen(
    state: UserDetailsContract.UiState,
    eventPublisher: (UserDetailsContract.UiEvent) -> Unit,
    onClose: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    AppBarIcon(
                        icon = Icons.AutoMirrored.Default.ArrowBack,
                        onClick = onClose,
                    )
                },
                title = {
                    Text(text = stringResource(Res.string.user_details_title))
                },
                actions = {
                    AppBarIcon(
                        icon = Icons.Default.Delete,
                        onClick = { eventPublisher(UserDetailsContract.UiEvent.DeleteCustomer) },
                    )
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
            ) {
                if (state.customer?.name != null) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 8.dp),
                        text = state.customer.name,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                }

                if (state.customer?.email != null) {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = state.customer.email
                    )
                }
            }
        },
    )
}
