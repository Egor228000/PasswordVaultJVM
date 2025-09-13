package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.CustomUIComposable.CustomTextDescription
import org.example.project.utils.handCursor
import org.example.project.viewModel.ViewModelPassword
import org.jetbrains.compose.resources.painterResource
import passwordvaultjvm.composeapp.generated.resources.Res
import passwordvaultjvm.composeapp.generated.resources.add
import passwordvaultjvm.composeapp.generated.resources.bank
import passwordvaultjvm.composeapp.generated.resources.discord_svgrepo_com
import passwordvaultjvm.composeapp.generated.resources.docker
import passwordvaultjvm.composeapp.generated.resources.ebay
import passwordvaultjvm.composeapp.generated.resources.epic_games
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModelPassword: ViewModelPassword) {
    val iconList = remember { mutableStateListOf(
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
    ) }

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

    Column {

        CenterAlignedTopAppBar(
            title = {
                Text("Редактирование", color = Color.White)
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
            Box {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    CustomOutlinedTextField(
                        value = nameService,
                        onValueChange = { nameService = it },
                        placeholder = "Google",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textTitle = "Название/Сервис"
                    )
                    CustomOutlinedTextField(
                        value = loginEmail,
                        onValueChange = { loginEmail = it },
                        placeholder = "user@gmail.com",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textTitle = "Логин/Email"
                    )
                    CustomOutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "12345",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textTitle = "Пароль",
                        trailingIcon = {
                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .handCursor()
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.search),
                                    null
                                )
                            }
                        }
                    )
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
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(50.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(iconList) { icon ->
                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .border(2.dp, Color.Red, shape = CircleShape)

                            ) {
                                Icon(
                                    painter = painterResource(icon),
                                    null,
                                    modifier = Modifier
                                        .size(30.dp)


                                )
                            }

                        }
                    }


                }


            }

        }
    }
}
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    textTitle: String,
    singleLine: Boolean = true
) {
    Column {

        CustomTextDescription(
            text = textTitle,
            fontSize = 15.sp
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(15),
            placeholder = {
                CustomTextDescription(
                    placeholder,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp
                )
            },
            singleLine = singleLine,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer
            ),
            textStyle = TextStyle(
                fontSize = 17.sp
            ),
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            modifier = modifier
        )
    }
}