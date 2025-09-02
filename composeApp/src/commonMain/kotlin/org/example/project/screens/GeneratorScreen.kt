package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.utils.handCursor
import org.jetbrains.compose.resources.painterResource
import passwordvaultjvm.composeapp.generated.resources.Res
import passwordvaultjvm.composeapp.generated.resources.copy
import passwordvaultjvm.composeapp.generated.resources.restart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneratorScreen() {
    val stateSlider = remember {
        SliderState(
            0f,
            valueRange = 4f..20f,
        )
    }
    var includeDigits = remember { mutableStateOf(false) }
    var includeSymbols = remember { mutableStateOf(false) }
    var includeUppercase = remember { mutableStateOf(false) }

    var slideNumber = stateSlider.value.toInt()

    Column {
        CenterAlignedTopAppBar(
            title = {
                Text("Генератор паролей", color = Color.White)
            },
            colors = TopAppBarDefaults.topAppBarColors(Color(0xFF121212)),
            modifier = Modifier
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .background(Color(0xFF121212))
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(15),
                colors = CardDefaults.cardColors(
                    Color(0xFF1E1E1E)
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {
                    Text(
                        "12345678911234912222",
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .handCursor()
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.restart),
                                null,
                                tint = Color(0xFFBA85FA)
                            )
                        }
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .handCursor()
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.copy),
                                null,
                                tint = Color(0xFFBA85FA)
                            )
                        }
                    }

                }
            }

            Card(
                shape = RoundedCornerShape(5),
                colors = CardDefaults.cardColors(
                    Color(0xFF1E1E1E)
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
                        Text(
                            "Длина пароля",
                            color = Color(0xFFB8B8B8),
                            fontSize = 16.sp,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                        )
                        Text(
                            slideNumber.toString(),
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                        )
                    }

                    Slider(
                        stateSlider,
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFFBA85FA),
                            activeTrackColor = Color(0xFFBA85FA),
                        )
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            "4",
                            color = Color(0xFFB8B8B8),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                        )
                        Text(
                            "20",
                            color = Color(0xFFB8B8B8),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                        )
                    }

                    FiltersGeneratorPassword(
                        "Заглавные буквы (A-Z)",
                        includeDigits
                    )
                    FiltersGeneratorPassword(
                        "Цифры (0-9)",
                        includeSymbols
                    )
                    FiltersGeneratorPassword(
                        "Спецсимволы (!_@#&)",
                        includeUppercase,
                        false
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
            Text(
                name,
                modifier = Modifier
                    .align(Alignment.CenterStart),
                color = Color.White,
                fontSize = 17.sp
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