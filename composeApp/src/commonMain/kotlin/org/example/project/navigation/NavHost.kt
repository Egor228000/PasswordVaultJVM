package org.example.project.navigation

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.project.screens.DetailScreen
import org.example.project.screens.GeneratorScreen
import org.example.project.screens.MainScreen
import org.example.project.screens.SettingScreen
import org.example.project.screens.SplashPasswordScreen


enum class Screens(val route: String) {

    SplashPassword("splashPassword"),
    Main("main"),
    Detail("detail"),
    Generator("generator"),
    Setting("setting")
}

@Composable
fun NavHosting(navControll: NavHostController) {
    NavHost(
        navController = navControll,
        startDestination = Screens.Main.route
    ) {
        composable(Screens.SplashPassword.route) {
            SplashPasswordScreen()
        }
        composable(Screens.Main.route) {
            MainScreen()
        }

        composable(Screens.Detail.route) {
            DetailScreen()
        }

        composable(Screens.Generator.route) {
            GeneratorScreen()
        }

        composable(Screens.Setting.route) {
            SettingScreen()
        }
    }
}