package org.example.project

import androidx.compose.runtime.Composable

@Composable
expect fun BiometricButton(
    onSuccess: () -> Unit = {},
    onFailure: () -> Unit = {}
)
