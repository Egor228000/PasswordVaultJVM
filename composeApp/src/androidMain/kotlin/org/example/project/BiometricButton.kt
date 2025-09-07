package org.example.project

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.launch
import org.example.project.utils.getBiometricAuthenticator

@SuppressLint("ContextCastToActivity")
@Composable
actual fun BiometricButton(
    onSuccess: () -> Unit,
    onFailure: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as FragmentActivity
    val authenticator = remember { getBiometricAuthenticator(activity) }

    TextButton(
        onClick = {
            scope.launch {
                val success = authenticator.authenticate("Подтвердите личность")
                if (success) {
                    println("✅ Успешный вход")
                    onSuccess()
                } else {
                    println("❌ Ошибка входа")
                    onFailure()
                }
            }
        }
    ) {
        Text("Использовать биометрию", color = Color(0xFFBA85FA))
    }
}
