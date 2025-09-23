package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import org.example.project.CustomUIComposable.CustomTextDescription
import org.example.project.CustomUIComposable.CustomTextTitle
import org.example.project.viewModel.ViewModelPassword
import org.jetbrains.compose.resources.painterResource
import passwordvaultjvm.composeapp.generated.resources.Res
import passwordvaultjvm.composeapp.generated.resources.password

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(viewModelPassword: ViewModelPassword) {
    var check by remember { mutableStateOf(false) }
    Column {

        CenterAlignedTopAppBar(
            title = {
                Text("Настройки", color = Color.White)
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
            CustomTextDescription(
                "Безопасноть",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500
            )
            CardSettings(
                "PIN код",
                "Включите или отключите вход по PIN-коду",
                check,
                onCheckedChange = {check = it}
            )
            CustomTextDescription(
                "Данные",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500

            )
            CardExportPassword()
            CustomTextDescription(
                "О программе",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500

            )
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),

                ) {
                    CustomTextTitle(
                            "Версия",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                    )
                    CustomTextDescription(
                        "1.0.0",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    )


                }
            }

        }
    }
}


@Composable
fun CardExportPassword() {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                CustomTextTitle(
                    "Экспорт паролей",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.password),
                        null
                    )
                }


            }
            HorizontalDivider(Modifier.padding(horizontal = 16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                CustomTextTitle(
                    "Импорт паролей",
                    modifier = Modifier
                        .align(Alignment.CenterStart)

                )
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.password),
                        null
                    )
                }

            }
        }
    }

}

@Composable
fun CardSettings(
    title: String,
    description: String,
    onCheckSwich: Boolean,
    onCheckedChange: ((Boolean) -> Unit)
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {

                CustomTextTitle(
                    title
                )
                CustomTextDescription(
                    description
                )
            }
            Switch(
                checked = onCheckSwich,
                onCheckedChange =  onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFFBA85FA)

                ),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.CenterEnd)

            )
        }
    }
}


