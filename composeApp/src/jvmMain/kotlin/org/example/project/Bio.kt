package org.example.project

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
actual fun BiometricButton(
    onSuccess: () -> Unit,
    onFailure: () -> Unit
) {
    TextButton(
        onClick = {
            println("Биометрия недоступна на Desktop, имитируем вход")
            onFailure()
        }
    ) {
        Text("Биометрия недоступна", color = Color.Gray)
    }
}
