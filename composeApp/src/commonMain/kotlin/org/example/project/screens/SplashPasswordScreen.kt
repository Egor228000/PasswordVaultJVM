package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.input.key.Key
import androidx.navigation.NavHostController
import io.github.kdroidfilter.platformtools.OperatingSystem
import io.github.kdroidfilter.platformtools.darkmodedetector.isSystemInDarkMode
import io.github.kdroidfilter.platformtools.getOperatingSystem
import org.example.project.CustomUIComposable.CustomTextDescription
import org.example.project.CustomUIComposable.CustomTextTitle
import org.example.project.navigation.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashPasswordScreen(navControll: NavHostController) {

    var errorPassword = remember { mutableStateOf(false) }

        Column {

            CenterAlignedTopAppBar(
                title = {
                    Text("Менеджер паролей", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
                modifier = Modifier
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,//0xFF121212
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                CustomTextTitle(
                    "Введите ваш PIN-код",
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                CustomTextDescription(
                    "Для доступа к вашим паролям",

                )

                Spacer(modifier = Modifier.padding(top = 32.dp))

                PinCodeInput(
                    errorPassword = errorPassword
                ) { pin ->
                    if (pin == "22875") {
                        navControll.navigate(Screens.Main.route)
                    } else {
                        errorPassword.value = true
                    }
                }
                if (errorPassword.value) {
                    Text("Указан неверный пароль", color = Color(0xFF990000))

                }
                Spacer(modifier = Modifier.padding(top = 8.dp))

                when (val os = getOperatingSystem()) {
                    OperatingSystem.WINDOWS -> {}
                    OperatingSystem.LINUX -> {}
                    OperatingSystem.ANDROID -> {
                        TextButton(
                            onClick = {

                            }
                        ) {
                            Text("Использовать биометрию", color = Color(0xFFBA85FA))
                        }
                    }

                    else -> {

                    }
                }


            }
        }
}

@Composable
fun PinCodeInput(
    length: Int = 5,
    errorPassword: MutableState<Boolean>,
    onPinEntered: (String) -> Unit
) {
    var code by remember { mutableStateOf(List(length) { "" }) }
    val focusRequesters = remember { List(length) { FocusRequester() } }

    Row {
        code.forEachIndexed { index, value ->
            OutlinedTextField(
                value = value,
                onValueChange = { newValue: String ->
                    if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                        val updated = code.toMutableList()
                        updated[index] = newValue
                        code = updated

                        if (code.all { it.isNotEmpty() }) {
                            onPinEntered(code.joinToString(""))
                        } else if (newValue.isNotEmpty() && index < length - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    }
                },
                isError = errorPassword.value,
                shape = RoundedCornerShape(15),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xFFB8B8B8),
                    unfocusedContainerColor = Color(0xFF1E1E1E),
                    focusedContainerColor = Color(0xFF1E1E1E),
                    focusedIndicatorColor = Color(0xFFBA85FA),
                    errorContainerColor = Color(0xFF1E1E1E),
                ),
                modifier = Modifier
                    .padding(4.dp)
                    .width(60.dp)
                    .focusRequester(focusRequesters[index])
                    .onKeyEvent { keyEvent ->
                        if (keyEvent.key == Key.Backspace && code[index].isEmpty() && index > 0) {
                            val updated = code.toMutableList()
                            updated[index - 1] = ""
                            code = updated
                            focusRequesters[index - 1].requestFocus()
                            true
                        } else {
                            false
                        }
                    },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, color = Color.White)
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequesters.first().requestFocus()
    }
}