package org.example.project.utils

import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import org.example.project.AndroidBiometricAuthenticator

actual fun Modifier.handCursor(): Modifier = this

actual fun getBiometricAuthenticator(activity: Any?): BiometricAuthenticator {
    return AndroidBiometricAuthenticator(activity as FragmentActivity)
}