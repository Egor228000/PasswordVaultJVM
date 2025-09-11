package org.example.project.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.project.screens.DetailScreen
import org.example.project.screens.GeneratorScreen
import org.example.project.screens.MainScreen
import org.example.project.screens.SettingScreen
import org.example.project.screens.SplashPasswordScreen
import org.example.project.viewModel.ViewModelPassword


enum class Screens(val route: String) {
    SplashPassword("splashPassword"),
    Main("main"),
    Detail("detail"),
    Generator("generator"),
    Setting("setting")
}

@Composable
fun NavHosting(navControll: NavHostController, paddingValues: PaddingValues, viewModelPassword: ViewModelPassword) {
    NavHost(
        navController = navControll,
        startDestination = Screens.SplashPassword.route,
        modifier = Modifier.padding(paddingValues),
        enterTransition = {
            fadeIn(animationSpec = tween(0))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(0))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(0))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(0))
        }

    ) {
        composable(Screens.SplashPassword.route) {
            SplashPasswordScreen(navControll)
        }
        composable(Screens.Main.route) {
            MainScreen(viewModelPassword)
        }

        composable(Screens.Detail.route) {
            DetailScreen(viewModelPassword)
        }

        composable(Screens.Generator.route) {
            GeneratorScreen(viewModelPassword)
        }

        composable(Screens.Setting.route) {
            SettingScreen(viewModelPassword)
        }
    }

}