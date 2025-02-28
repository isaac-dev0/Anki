package com.isaac_dolphin.anki.presentation.fragment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.isaac_dolphin.anki.presentation.NavigationScreen

private val authenticatedNavigationItems = listOf(
    NavigationScreen.Home,
    NavigationScreen.Learn,
    NavigationScreen.Profile
)

private fun getNavigationBarItems(isAuthenticated: Boolean): List<NavigationScreen> {
    return if (isAuthenticated) authenticatedNavigationItems else emptyList()
}

@Composable
fun NavigationBarFragment(
    navController: NavHostController,
    isAuthenticated: Boolean
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        getNavigationBarItems(isAuthenticated).forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.label)
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.label),
                        fontSize = 12.sp
                    )
                },
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { startDestination ->
                            popUpTo(startDestination) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}