package org.example.project.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntryDecorator

import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable
import org.example.project.screens.DetailScreen
import org.example.project.screens.GeneratorScreen
import org.example.project.screens.MainScreen
import org.example.project.screens.SettingScreen
import org.example.project.screens.SplashPasswordScreen
import org.example.project.viewModel.ViewModelPassword

@Serializable
data object SplashPassword: NavKey
@Serializable
data object Main: NavKey
@Serializable
data class Detail(val id: Long): NavKey
@Serializable
data object Generator: NavKey
@Serializable
data object Setting: NavKey



@Composable
fun NavHosting(backStack: SnapshotStateList<NavKey>, paddingValues: PaddingValues, viewModelPassword: ViewModelPassword) {

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<SplashPassword> {
                SplashPasswordScreen(backStack)
            }
            entry<Main> {
                MainScreen(viewModelPassword)
            }
            entry<Generator> {
                GeneratorScreen(viewModelPassword)
            }
            entry<Setting> {
                SettingScreen(viewModelPassword)
            }
            entry<Detail> {
                DetailScreen(viewModelPassword)
            }
        }
    )
}