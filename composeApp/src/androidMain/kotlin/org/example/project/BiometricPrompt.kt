package org.example.project

import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import org.example.project.utils.BiometricAuthenticator
import kotlin.coroutines.resume

class AndroidBiometricAuthenticator(
    private val activity: FragmentActivity
) : BiometricAuthenticator {

    override suspend fun authenticate(reason: String): Boolean {
        return suspendCancellableCoroutine { cont ->
            val executor = ContextCompat.getMainExecutor(activity)

            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Вход по биометрии")
                .setSubtitle(reason)
                .setNegativeButtonText("Отмена")
                .build()

            val biometricPrompt = BiometricPrompt(
                activity,
                executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        cont.resume(true)
                    }
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        cont.resume(false)
                    }
                    override fun onAuthenticationFailed() {
                        cont.resume(false)
                    }
                }
            )

            biometricPrompt.authenticate(promptInfo)
        }
    }
}


