package org.example.project

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.navigation.compose.rememberNavController
import org.example.project.navigation.NavHosting
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
        window.minimumSize = Dimension(400, 900)
        val navControll = rememberNavController()

        NavHosting(navControll)
    }
}