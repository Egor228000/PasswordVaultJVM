package org.example.project.utils

import androidx.compose.ui.Modifier

expect fun Modifier.handCursor(): Modifier

interface BiometricAuthenticator {
    suspend fun authenticate(reason: String): Boolean
}

expect fun getBiometricAuthenticator(activity: Any? = null): BiometricAuthenticator
