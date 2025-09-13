package org.example.project.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import org.example.project.DesktopBiometricAuthenticator
import org.jetbrains.skiko.Cursor

actual fun Modifier.handCursor(): Modifier =
    this.pointerHoverIcon(PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)))

actual fun getBiometricAuthenticator(activity: Any?): BiometricAuthenticator {
    return DesktopBiometricAuthenticator()
}

