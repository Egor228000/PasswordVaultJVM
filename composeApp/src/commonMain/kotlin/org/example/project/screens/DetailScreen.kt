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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.launch
import org.example.project.CustomUIComposable.CustomButton
import org.example.project.CustomUIComposable.CustomOutlinedTextField
import org.example.project.CustomUIComposable.CustomTextDescription
import org.example.project.DatabaseManager
import org.example.project.navigation.Detail
import org.example.project.navigation.Main
import org.example.project.room.PasswordCard
import org.example.project.utils.handCursor
import org.example.project.viewModel.ViewModelPassword
import org.jetbrains.compose.resources.painterResource
import passwordvaultjvm.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModelPassword: ViewModelPassword, backStack: SnapshotStateList<NavKey>, key: Detail) {

    var cardPassword = remember { mutableStateOf<PasswordCard?>(null) }

    val iconMap = mapOf(
        1 to Res.drawable.bank,
        2 to Res.drawable.discord_svgrepo_com,
        3 to Res.drawable.docker,
        4 to Res.drawable.ebay,
        5 to Res.drawable.github,
        6 to Res.drawable.gmail,
        7 to Res.drawable.google,
        8 to Res.drawable.googlecalendar,
        9 to Res.drawable.googleplay,
        10 to Res.drawable.insta,
        11 to Res.drawable.netflix,
        12 to Res.drawable.openai,
        13 to Res.drawable.skypeforbusiness,
        14 to Res.drawable.slack,
        15 to Res.drawable.spotify,
        16 to Res.drawable.stackoverflow,
        17 to Res.drawable.steam,
        18 to Res.drawable.telegram,
        19 to Res.drawable.tiktok,
        20 to Res.drawable.vk,
        21 to Res.drawable.whatsapp,
        22 to Res.drawable.windows,
        23 to Res.drawable.youtube
    )


    var selectedIconId by remember { mutableStateOf(23) }


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
    LaunchedEffect(key.id.toInt() != 777) {
        val card = DatabaseManager.getPasswordCardById(key.id)
        cardPassword.value = card

        card?.let {
            selectedIconId = it.avatar
            nameService = it.name
            loginEmail = it.login
            password = it.password
            description = it.description
        }
    }



    val scope = rememberCoroutineScope()
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
                    items(iconMap.keys.toList()) { id ->
                        val isSelected = selectedIconId == id
                        val icon = iconMap[id]!!


                        IconButton(
                            onClick = { selectedIconId = id },
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
                            scope.launch {
                                DatabaseManager.insertPasswordCard(
                                    name = nameService,
                                    login = loginEmail,
                                    description = description,
                                    password = password,
                                    avatar = selectedIconId
                                )
                            }

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

