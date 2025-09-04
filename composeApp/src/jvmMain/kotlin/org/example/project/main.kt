package org.example.project

import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.navigation.compose.rememberNavController
import io.github.kdroidfilter.platformtools.darkmodedetector.isSystemInDarkMode
import io.github.kdroidfilter.platformtools.darkmodedetector.windows.setWindowsAdaptiveTitleBar
import org.example.project.Theme.DarkColors
import org.example.project.Theme.LightColors
import org.example.project.Theme.MyAppTheme
import org.example.project.navigation.NavigationBottom
import org.example.project.viewModel.ViewModelPassword
import java.awt.Dimension

fun main() = application {
    val state = rememberWindowState(
        position = WindowPosition(Alignment.Center),
        size = DpSize(700.dp, 900.dp)
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "PasswordVaultJVM",
        state = state
    ) {
        window.setWindowsAdaptiveTitleBar()
        window.minimumSize = Dimension(400, 900)
        val navControll = rememberNavController()

        val viewModelPassword = remember { ViewModelPassword() }
        MyAppTheme(
            colorScheme = if (isSystemInDarkMode()) DarkColors else LightColors
        ) {
            NavigationBottom(navControll, viewModelPassword)
        }
    }
}