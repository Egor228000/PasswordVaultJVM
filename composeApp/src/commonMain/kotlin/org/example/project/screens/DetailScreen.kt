package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.isBackPressed
import androidx.compose.ui.input.pointer.isForwardPressed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import org.example.project.CustomUIComposable.CustomButton
import org.example.project.CustomUIComposable.CustomOutlinedTextField
import org.example.project.CustomUIComposable.CustomTextDescription
import org.example.project.navigation.Detail
import org.example.project.navigation.Main
import org.example.project.room.DatabaseManager
import org.example.project.utils.handCursor
import org.example.project.viewModel.ViewModelPassword
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import passwordvaultjvm.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModelPassword: ViewModelPassword, backStack: SnapshotStateList<NavKey>, key: Detail) {
    val iconList = remember {
        mutableStateListOf(
            Res.drawable.bank,
            Res.drawable.discord_svgrepo_com,
            Res.drawable.docker,
            Res.drawable.ebay,
            Res.drawable.github,
            Res.drawable.gmail,
            Res.drawable.google,
            Res.drawable.googlecalendar,
            Res.drawable.googleplay,
            Res.drawable.insta,
            Res.drawable.netflix,
            Res.drawable.openai,
            Res.drawable.skypeforbusiness,
            Res.drawable.slack,
            Res.drawable.spotify,
            Res.drawable.stackoverflow,
            Res.drawable.steam,
            Res.drawable.telegram,
            Res.drawable.tiktok,
            Res.drawable.vk,
            Res.drawable.whatsapp,
            Res.drawable.windows,
            Res.drawable.youtube
        )
    }

    var selectedIcon by remember { mutableStateOf<DrawableResource>(Res.drawable.youtube) }

    var nameService by remember {
        mutableStateOf("")
    }
    var loginEmail by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        val buttons = event.buttons
                        when {
                            buttons.isBackPressed -> backStack.add(Main)
                        }
                    }
                }
            }
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    if (key.id.toInt() == 777) "Добавление" else "Редактирование/Просмотр", color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(Color(0xFF121212)),
            modifier = Modifier,
            navigationIcon = {
                IconButton(
                    onClick = { backStack.add(Main) }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.arrow_back),
                        null
                    )
                }
            }
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .background(Color(0xFF121212))
                .padding(16.dp)
                .fillMaxSize()
        ) {
            item {
                CustomOutlinedTextField(
                    value = nameService,
                    onValueChange = { nameService = it },
                    placeholder = "Google",
                    modifier = Modifier.fillMaxWidth(),
                    textTitle = "Название/Сервис"
                )
            }
            item {
                CustomOutlinedTextField(
                    value = loginEmail,
                    onValueChange = { loginEmail = it },
                    placeholder = "user@gmail.com",
                    modifier = Modifier.fillMaxWidth(),
                    textTitle = "Логин/Email"
                )
            }
            item {
                CustomOutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "12345",
                    modifier = Modifier.fillMaxWidth(),
                    textTitle = "Пароль",
                    trailingIcon = {
                        IconButton(
                            onClick = {},
                            modifier = Modifier.handCursor()
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.search),
                                contentDescription = null
                            )
                        }
                    }
                )
            }
            item {
                CustomOutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = "Дополнительная информация",
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    textTitle = "Описание/Заметки",
                    singleLine = false
                )
                CustomTextDescription(
                    text = "Выбрать иконку",
                    fontSize = 15.sp
                )
            }

            item {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(iconList) { icon ->
                        val isSelected = selectedIcon == icon

                        IconButton(
                            onClick = { selectedIcon = icon },
                            modifier = Modifier
                                .size(60.dp)
                                .padding(4.dp)
                                .border(
                                    width = if (isSelected) 2.dp else 0.dp,
                                    color = if (isSelected) Color.Blue else Color.Transparent,
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                painter = painterResource(icon),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomButton(
                        text = "Отмена",
                        onClick = { backStack.add(Main) },
                        modifier = Modifier.weight(1f).padding(end = 8.dp),
                        color = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB8B8B8)
                        )
                    )
                    CustomButton(
                        text = "Сохранить",
                        onClick = {
                            DatabaseManager.insertPasswordCard(
                                name = nameService,
                                description = description,
                                password = password,
                                avatar = selectedIcon.toString()
                            )
                        },
                        modifier = Modifier.weight(1f).padding(start = 8.dp),
                        color = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }
        }
    }
}

