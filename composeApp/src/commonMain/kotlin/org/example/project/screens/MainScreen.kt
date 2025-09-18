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
import org.example.project.CustomUIComposable.CustomOutlinedTextField
import org.example.project.CustomUIComposable.CustomTextDescription
import org.example.project.CustomUIComposable.CustomTextTitle
import org.example.project.navigation.Detail
import org.example.project.room.DatabaseManager
import org.example.project.room.PasswordCard
import org.example.project.utils.handCursor
import org.example.project.viewModel.ViewModelPassword
import org.jetbrains.compose.resources.painterResource
import passwordvaultjvm.composeapp.generated.resources.Res
import passwordvaultjvm.composeapp.generated.resources.add
import passwordvaultjvm.composeapp.generated.resources.copy
import passwordvaultjvm.composeapp.generated.resources.search


@Suppress("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModelPassword: ViewModelPassword, backStack: SnapshotStateList<NavKey>) {

    var listPassword = remember { mutableStateListOf<PasswordCard>() }

    /*LaunchedEffect(listPassword.isNotEmpty()) {
        DatabaseManager.getAllCards { list ->
            listPassword.addAll(list)
        }
    }*/
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
                            colors = IconButtonDefaults.iconButtonColors(Color(0xFFBA85FA)),
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
            .clickable(onClick = { backStack.add(Detail(card.id)) })

    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15))
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .size(50.dp)
            ) {
                Icon(
                    painter = painterResource(card.avatar),
                    null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier

                    .padding(start = 16.dp)
            ) {
                val description = card.description
                CustomTextTitle(
                    card.name,
                )

                CustomTextDescription(
                    text = if (description.length <= 21) description else description.take(18).plus("..."),
                )

            }
            val password = card.password
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .handCursor()
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.copy),
                        null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

            }
        }

    }
}




// commonMain
