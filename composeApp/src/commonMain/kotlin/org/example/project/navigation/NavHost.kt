package org.example.project.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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


enum class Screens(val route: String) {

    SplashPassword("splashPassword"),
    Main("main"),
    Detail("detail"),
    Generator("generator"),
    Setting("setting")
}

@Composable
fun NavHosting(navControll: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navControll,
        startDestination = Screens.Main.route,
        modifier = Modifier.padding(paddingValues),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(300)
            )
        },

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