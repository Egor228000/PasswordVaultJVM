package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.Dispatchers
import org.example.project.CustomUIComposable.CustomOutlinedTextField
import org.example.project.CustomUIComposable.CustomTextDescription
import org.example.project.CustomUIComposable.CustomTextTitle
import org.example.project.DatabaseManager
import org.example.project.navigation.Detail
import org.example.project.room.PasswordCard
import org.example.project.utils.handCursor
import org.example.project.viewModel.ViewModelPassword
import org.jetbrains.compose.resources.painterResource
import passwordvaultjvm.composeapp.generated.resources.Res
import passwordvaultjvm.composeapp.generated.resources.add
import passwordvaultjvm.composeapp.generated.resources.bank
import passwordvaultjvm.composeapp.generated.resources.copy
import passwordvaultjvm.composeapp.generated.resources.discord_svgrepo_com
import passwordvaultjvm.composeapp.generated.resources.docker
import passwordvaultjvm.composeapp.generated.resources.ebay
import passwordvaultjvm.composeapp.generated.resources.github
import passwordvaultjvm.composeapp.generated.resources.gmail
import passwordvaultjvm.composeapp.generated.resources.google
import passwordvaultjvm.composeapp.generated.resources.googlecalendar
import passwordvaultjvm.composeapp.generated.resources.googleplay
import passwordvaultjvm.composeapp.generated.resources.insta
import passwordvaultjvm.composeapp.generated.resources.netflix
import passwordvaultjvm.composeapp.generated.resources.openai
import passwordvaultjvm.composeapp.generated.resources.search
import passwordvaultjvm.composeapp.generated.resources.skypeforbusiness
import passwordvaultjvm.composeapp.generated.resources.slack
import passwordvaultjvm.composeapp.generated.resources.spotify
import passwordvaultjvm.composeapp.generated.resources.stackoverflow
import passwordvaultjvm.composeapp.generated.resources.steam
import passwordvaultjvm.composeapp.generated.resources.telegram
import passwordvaultjvm.composeapp.generated.resources.tiktok
import passwordvaultjvm.composeapp.generated.resources.vk
import passwordvaultjvm.composeapp.generated.resources.whatsapp
import passwordvaultjvm.composeapp.generated.resources.windows
import passwordvaultjvm.composeapp.generated.resources.youtube


@Suppress("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModelPassword: ViewModelPassword, backStack: SnapshotStateList<NavKey>) {

    var listPassword = remember { mutableStateListOf<PasswordCard>() }

    LaunchedEffect(Unit) {
        val list = DatabaseManager.getAllPasswordCards()
        listPassword.clear()
        listPassword.addAll(list)
    }
    var searchPassword by remember { mutableStateOf("") }

    Column(
    ) {

        CenterAlignedTopAppBar(
            title = {
                Text("Пароли", color = MaterialTheme.colorScheme.primary)
            },
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.secondary),
            modifier = Modifier
        )
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .padding(horizontal = 16.dp)
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
            ) {

                CustomOutlinedTextField(
                    value = searchPassword,
                    onValueChange = { searchPassword = it },
                    placeholder = "Поиск паролей",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth(),
                    textTitle = "",
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(Res.drawable.search),
                            null,
                            tint = Color(0xFFB8B8B8)
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {backStack.add(Detail(777))},
                            colors  = IconButtonDefaults.iconButtonColors(Color(0xFFBA85FA)),
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .handCursor()
                        ) {
                            Icon(painter = painterResource(Res.drawable.add), null)

                        }
                    }
                )
            }

            Box(
                modifier = Modifier
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(300.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 86.dp)
                ) {
                    items(listPassword) { card ->
                        var isHovered = mutableStateOf(false)
                        CardPassword(isHovered = isHovered, backStack, card)
                    }
                }


            }

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CardPassword(
    isHovered: MutableState<Boolean>,
    backStack: SnapshotStateList<NavKey>,
    card: PasswordCard
) {
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

    val iconRes = iconMap[card.avatar] ?: Res.drawable.bank

    Card(
        shape = RoundedCornerShape(15),
        colors = CardDefaults.cardColors(
            if (!isHovered.value) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.primaryContainer.copy(0.3f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15))
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        if (event.type == PointerEventType.Enter) {
                            isHovered.value = true
                        } else if (event.type == PointerEventType.Exit) {
                            isHovered.value = false
                        }
                    }
                }
            }
            .clickable { backStack.add(Detail(card.id)) }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(15))
                    .background(MaterialTheme.colorScheme.onSecondary)
            ) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                CustomTextTitle(card.name)

                CustomTextDescription(
                    text = card.login,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            IconButton(
                onClick = {},
                modifier = Modifier.handCursor()
            ) {
                Icon(
                    painter = painterResource(Res.drawable.copy),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}




