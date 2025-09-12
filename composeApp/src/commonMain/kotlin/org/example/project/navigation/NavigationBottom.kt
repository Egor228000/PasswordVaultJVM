package org.example.project.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.NavKey
import org.example.project.viewModel.ViewModelPassword
import org.jetbrains.compose.resources.painterResource
import passwordvaultjvm.composeapp.generated.resources.Res
import passwordvaultjvm.composeapp.generated.resources.key
import passwordvaultjvm.composeapp.generated.resources.password
import passwordvaultjvm.composeapp.generated.resources.setting


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBottom(backStack: SnapshotStateList<NavKey>, viewModelPassword: ViewModelPassword) {

    var selectedIndex by remember { mutableStateOf(1) }

    Scaffold(
        bottomBar = {
            if (backStack.lastOrNull() == SplashPassword) {

            } else {

                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                ) {
                    NavigationBarItem(
                        selected = selectedIndex == 1,
                        onClick = {
                            selectedIndex = 1
                            backStack.add(Main)
                        },
                        icon = { Icon(painter = painterResource(Res.drawable.key), null) },
                        label = { Text("Пароли") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            indicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    NavigationBarItem(
                        selected = selectedIndex == 2,
                        onClick = {
                            selectedIndex = 2
                            backStack.add(Generator)

                        },
                        icon = { Icon(painter = painterResource(Res.drawable.password), null) },
                        label = { Text("Генератор") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            indicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    NavigationBarItem(
                        selected = selectedIndex == 3,
                        onClick = {
                            selectedIndex = 3
                            backStack.add(Setting)

                        },
                        icon = { Icon(painter = painterResource(Res.drawable.setting), null) },
                        label = { Text("Настройки") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            indicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }
        },
        content = { paddingValues ->
            NavHosting(backStack, paddingValues, viewModelPassword)
        }
    )

}
