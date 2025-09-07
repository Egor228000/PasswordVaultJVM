package org.example.project

import org.example.project.utils.BiometricAuthenticator

class DesktopBiometricAuthenticator : BiometricAuthenticator {
    override suspend fun authenticate(reason: String): Boolean {
        println("Биометрия недоступна на Desktop")
        return false
    }
}

