package com.appollo41.app.users.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appollo41.app.resources.Res
import com.appollo41.app.resources.compose_multiplatform
import com.appollo41.app.resources.user_list_item
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserListScreen(
    viewModel: UserListViewModel,
    onUserClick: (userId: Long) -> Unit,
) {

    val state by viewModel.state.collectAsState()

    UserListScreen(
        state = state,
        eventPublisher = viewModel::setEvent,
        onUserClick = onUserClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserListScreen(
    state: UserListContract.UiState,
    eventPublisher: (UserListContract.UiEvent) -> Unit,
    onUserClick: (userId: Long) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    Image(
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null,
                    )
                },
                title = {
                    Text(text = "Compose Multiplatfrom")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 8.dp, end = 8.dp),
                onClick = {
                    eventPublisher(UserListContract.UiEvent.GenerateRandomUser)
                },
                shape = CircleShape,
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add new random user",
                    )
                },
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                itemsIndexed(
                    items = state.customers,
                    key = { _, user -> user.id },
                ) { index, user ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .clickable { onUserClick(user.id) },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                                .width(48.dp),
                            text = "${(index + 1)}.",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                        )

                        Text(
                            text = stringResource(Res.string.user_list_item, user.name)
                        )
                    }
                }

                item {
                    // Spacer so FAB doesn't overlap with the
                    // last list item if list is scrollable
                    Spacer(modifier = Modifier.height(64.dp))
                }
            }
        },
    )
}
