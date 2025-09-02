package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.utils.handCursor
import org.jetbrains.compose.resources.painterResource
import passwordvaultjvm.composeapp.generated.resources.Res
import passwordvaultjvm.composeapp.generated.resources.add
import passwordvaultjvm.composeapp.generated.resources.copy
import passwordvaultjvm.composeapp.generated.resources.search


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    var searchPassword by remember { mutableStateOf("") }

    Column {

        CenterAlignedTopAppBar(
            title = {
                Text("Пароли", color = Color.White)
            },
            colors = TopAppBarDefaults.topAppBarColors(Color(0xFF121212)),
            modifier = Modifier
        )
        Column(
            modifier = Modifier
                .background(Color(0xFF121212))
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(80.dp)
            ) {

                OutlinedTextField(
                    value = searchPassword,
                    onValueChange = { searchPassword = it },
                    shape = RoundedCornerShape(15),
                    placeholder = { Text("Поиск паролей", color = Color(0xFFB8B8B8)) },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color(0xFFB8B8B8),
                        unfocusedContainerColor = Color(0xFF1E1E1E),
                        focusedContainerColor = Color(0xFF1E1E1E)
                    ),
                    textStyle = TextStyle(
                        fontSize = 17.sp
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(Res.drawable.search),
                            null,
                            tint = Color(0xFFB8B8B8)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                )
            }
            Box {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(300.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 76.dp)
                ) {
                    items(30) {
                        var isHovered = remember { mutableStateOf(false) }
                        CardPassword(isHovered)
                    }
                }

                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    FloatingActionButton(
                        onClick = {},
                        containerColor = Color(0xFFBA85FA)
                    ) {
                        Icon(painter = painterResource(Res.drawable.add), null)
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CardPassword(isHovered: MutableState<Boolean>) {


    Card(

        shape = RoundedCornerShape(15),
        colors = CardDefaults.cardColors(
            if (!isHovered.value) Color(0xFF1E1E1E)
            else Color(0xFF313131)
        ),
        modifier = Modifier
            .fillMaxWidth()
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

    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15))
                    .background(Color.Black)
                    .size(50.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.add),
                    null,
                    tint = Color(0xFFB8B8B8),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    "Google",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Goosdasdasdasdfsdfgle",
                    color = Color(0xFFB8B8B8),
                    fontSize = 14.sp,
                )
            }
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
                        tint = Color(0xFFB8B8B8)
                    )
                }

            }
        }

    }
}



data class CardPasswordData(
    val id: Int,
    val name: String,
    val description: String,
    val copy: String,
    val icon: Int
)

// commonMain
