package org.example.project.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import org.jetbrains.compose.resources.painterResource
import passwordvaultjvm.composeapp.generated.resources.Res
import passwordvaultjvm.composeapp.generated.resources.key
import passwordvaultjvm.composeapp.generated.resources.password
import passwordvaultjvm.composeapp.generated.resources.setting


@Composable
fun NavigationBottom(navControll: NavHostController) {

    var selectedIndex by remember { mutableStateOf(1) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF121212),
                modifier = Modifier
            ) {
                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = {selectedIndex = 1},
                    icon = { Icon(painter = painterResource(Res.drawable.key), null) },
                    label = { Text("Пароли") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        selectedTextColor =  Color(0xFFBA85FA),
                        indicatorColor = Color(0xFFBA85FA),
                    )
                )
                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = {selectedIndex = 2},
                    icon = { Icon(painter = painterResource(Res.drawable.password), null) },
                    label = { Text("Генератор") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        selectedTextColor =  Color(0xFFBA85FA),
                        indicatorColor = Color(0xFFBA85FA)
                    )
                )
                NavigationBarItem(
                    selected = selectedIndex == 3,
                    onClick = {selectedIndex = 3},
                    icon = { Icon(painter = painterResource(Res.drawable.setting), null) },
                    label = { Text("Настройки") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        selectedTextColor =  Color(0xFFBA85FA),
                        indicatorColor = Color(0xFFBA85FA)
                    )
                )
            }
        },
        content = { paddingValues ->
            NavHosting(navControll, paddingValues)
        }
    )
}