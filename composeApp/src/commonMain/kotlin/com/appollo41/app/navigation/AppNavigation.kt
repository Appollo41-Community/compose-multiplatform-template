package com.appollo41.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.appollo41.app.users.details.UserDetailsScreen
import com.appollo41.app.users.details.UserDetailsViewModel
import com.appollo41.app.users.list.UserListScreen
import com.appollo41.app.users.list.UserListViewModel
import org.koin.compose.viewmodel.koinViewModel

private fun NavController.navigateToUserDetails(customerId: Long) =
    navigate(route = "users/$customerId")

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "users",
    ) {
        usersList(
            route = "users",
            navController = navController
        )

        usersDetails(
            route = "users/{$CUSTOMER_ID}",
            arguments = listOf(
                navArgument(CUSTOMER_ID) {
                    type = NavType.LongType
                    nullable = false
                }
            ),
            navController = navController,
        )
    }
}

private fun NavGraphBuilder.usersList(
    route: String,
    navController: NavController,
) = composable(route = route) {
    val viewModel = koinViewModel<UserListViewModel>()
    UserListScreen(
        viewModel = viewModel,
        onUserClick = { userId -> navController.navigateToUserDetails(customerId = userId) },
    )
}


private fun NavGraphBuilder.usersDetails(
    route: String,
    arguments: List<NamedNavArgument>,
    navController: NavController,
) = composable(route = route, arguments = arguments) {
    val viewModel = koinViewModel<UserDetailsViewModel>()
    UserDetailsScreen(
        viewModel = viewModel,
        onClose = { navController.navigateUp() },
    )
}
