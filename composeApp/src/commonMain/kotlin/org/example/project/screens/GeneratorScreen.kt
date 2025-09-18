package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.example.project.CustomUIComposable.CustomTextDescription
import org.example.project.CustomUIComposable.CustomTextTitle
import org.example.project.utils.handCursor
import org.example.project.viewModel.ViewModelPassword
import org.jetbrains.compose.resources.painterResource
import passwordvaultjvm.composeapp.generated.resources.Res
import passwordvaultjvm.composeapp.generated.resources.copy
import passwordvaultjvm.composeapp.generated.resources.restart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneratorScreen(viewModelPassword: ViewModelPassword) {
    val stateSlider = remember {
        SliderState(
            4f,
            valueRange = 4f..20f,
        )
    }
    var includeDigits = remember { mutableStateOf(false) }
    var includeSymbols = remember { mutableStateOf(false) }
    var includeUppercase = remember { mutableStateOf(false) }
    var slideNumber = stateSlider.value.toInt()
    var password by remember { mutableStateOf("") }
    val clipboardManager = LocalClipboardManager.current
    val scope  = rememberCoroutineScope()

    LaunchedEffect(slideNumber, includeUppercase.value, includeDigits.value, includeSymbols.value) {
        password = viewModelPassword.generatePassword(
            length = slideNumber,
            includeDigits = includeDigits.value,
            includeSymbols = includeSymbols.value,
            includeUppercase = includeUppercase.value
        )
    }
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text("Генератор паролей", color = MaterialTheme.colorScheme.primary)
            },
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.secondary),
            modifier = Modifier
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(15),
                colors = CardDefaults.cardColors(
                    MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {
                    CustomTextTitle(
                        password,
                        modifier = Modifier
                            .align(Alignment.CenterStart)

                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = {
                                password = viewModelPassword.generatePassword(
                                    length = slideNumber,
                                    includeDigits = includeDigits.value,
                                    includeSymbols = includeSymbols.value,
                                    includeUppercase = includeUppercase.value
                                )
                            },
                            modifier = Modifier
                                .handCursor()
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.restart),
                                null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(
                            onClick = {
                                scope.launch {
                                    clipboardManager.setText(annotatedString = AnnotatedString(password))
                                }
                            },
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

            Card(
                shape = RoundedCornerShape(5),
                colors = CardDefaults.cardColors(
                    MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        CustomTextTitle(
                            "Длина пароля",
                            fontSize = 16.sp,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                        )

                        CustomTextTitle(
                            slideNumber.toString(),
                            fontSize = 16.sp,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                        )

                    }

                    Slider(
                        stateSlider,
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            activeTrackColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        CustomTextDescription(
                            "4",
                            fontSize = 12.sp,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                        )
                        CustomTextDescription(
                            "20",
                            fontSize = 12.sp,
                            modifier = Modifier
                                .align(Alignment.TopEnd)

                        )
                    }

                    FiltersGeneratorPassword(
                        "Заглавные буквы (A-Z)",
                        includeUppercase
                    )
                    FiltersGeneratorPassword(
                        "Цифры (0-9)",
                        includeDigits
                    )
                    FiltersGeneratorPassword(
                        "Спецсимволы (!_@#&)",
                        includeSymbols,
                        false
                    )
                    CustomTextDescription(
                        text = " Взламают через: ${viewModelPassword.estimateCrackTime(password)}"

                    )
                }
            }
        }
    }
}

@Composable
fun FiltersGeneratorPassword(
    name: String,
    onOrof: MutableState<Boolean>,
    watchDivider: Boolean = true
) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomTextTitle(
                name,
                fontSize = 17.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .align(Alignment.CenterStart),
            )

            Switch(
                checked = onOrof.value,
                onCheckedChange =  {onOrof.value = it},
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFFBA85FA)

                ),
                modifier = Modifier
                    .align(Alignment.CenterEnd)

            )
        }

    }
    if (watchDivider) {
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 8.dp),
            color = Color(0xFF444444)
        )
    }

}


