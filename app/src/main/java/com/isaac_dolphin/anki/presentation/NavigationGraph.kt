package com.isaac_dolphin.anki.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.isaac_dolphin.anki.R
import com.isaac_dolphin.anki.presentation.screen.HomeScreen
import com.isaac_dolphin.anki.presentation.screen.LearnScreen
import com.isaac_dolphin.anki.presentation.screen.LoginScreen
import com.isaac_dolphin.anki.presentation.screen.ProfileScreen
import com.isaac_dolphin.anki.presentation.screen.SignupScreen

sealed interface NavigationScreen {
    val route: String
    @get:DrawableRes
    val icon: Int
    @get:StringRes
    val label: Int

    data object Login : NavigationScreen {
        override val route = "login"
        override val icon = R.drawable.home
        override val label = R.string.bottom_navigation_login_title
    }

    data object Signup : NavigationScreen {
        override val route = "signup"
        override val icon = R.drawable.home
        override val label = R.string.bottom_navigation_login_title
    }

    data object Home : NavigationScreen {
        override val route = "home"
        override val icon = R.drawable.home
        override val label = R.string.bottom_navigation_item1_title
    }

    data object Learn : NavigationScreen {
        override val route = "learn"
        override val icon = R.drawable.learning
        override val label = R.string.bottom_navigation_item2_title
    }

    data object Profile : NavigationScreen {
        override val route = "profile"
        override val icon = R.drawable.profile
        override val label = R.string.bottom_navigation_item3_title
    }

    companion object {
        val allScreens = listOf(Login, Home, Learn, Profile)
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    simulateLogin: () -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = NavigationScreen.Login.route,
        modifier = modifier
    ) {
        composable(NavigationScreen.Login.route) {
            LoginScreen(
                title = stringResource(NavigationScreen.Login.label),
                simulateLogin = {
                    simulateLogin()
                    navController.navigate(NavigationScreen.Home.route)
                },
                modifier = modifier
            )
        }
        composable(NavigationScreen.Signup.route) {
            SignupScreen(
                title = stringResource(NavigationScreen.Signup.label),
                modifier = modifier
            )
        }
        composable(NavigationScreen.Home.route) {
            HomeScreen(
                title = stringResource(NavigationScreen.Home.label),
                context = context,
                modifier = modifier
            )
        }
        composable(NavigationScreen.Learn.route) {
            LearnScreen(
                title = stringResource(NavigationScreen.Learn.label),
                modifier = Modifier
            )
        }
        composable(NavigationScreen.Profile.route) {
            ProfileScreen(
                title = stringResource(NavigationScreen.Profile.label),
                modifier = Modifier
            )
        }
    }
}