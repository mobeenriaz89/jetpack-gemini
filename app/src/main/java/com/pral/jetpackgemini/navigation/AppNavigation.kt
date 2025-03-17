package com.pral.jetpackgemini.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pral.jetpackgemini.ui.views.LoginScreen
import com.pral.jetpackgemini.ui.views.MainScreen
import com.pral.jetpackgemini.ui.views.SplashScreen

enum class Screen {
    SPLASH,
    HOME,
    LOGIN
}

sealed class NavigationItem(val route: String) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name)
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    startDestination: String = NavigationItem.Splash.route
) {
    val navController = rememberNavController()
    Scaffold(modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavigationItem.Splash.route) { SplashScreen(navController) }
            composable(NavigationItem.Login.route) { LoginScreen(navController) }
            composable(NavigationItem.Home.route) { MainScreen() }
        }
    }

}
