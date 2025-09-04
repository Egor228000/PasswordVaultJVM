package org.example.project.viewModel

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class ViewModelPassword(): ViewModel() {





    // Генерация пароля
    fun generatePassword(
        length: Int,
        includeDigits: Boolean,
        includeSymbols: Boolean,
        includeUppercase: Boolean
    ): String {
        val lowercase = ('a'..'z').toList()
        val uppercase = ('A'..'Z').toList()
        val digits = ('0'..'9').toList()
        val symbols = listOf('!', '_', '@', '#', '&', '$', '%', '*', '+', '-', '=', '?')

        val pool = buildList {
            addAll(lowercase)
            if (includeUppercase) addAll(uppercase)
            if (includeDigits) addAll(digits)
            if (includeSymbols) addAll(symbols)
        }.ifEmpty { lowercase } // fallback на строчные

        val required = mutableListOf<Char>().apply {
            if (includeUppercase) add(uppercase.random())
            if (includeDigits) add(digits.random())
            if (includeSymbols) add(symbols.random())
        }

        val result = mutableListOf<Char>().apply {
            addAll(required)
            repeat((length - required.size).coerceAtLeast(0)) {
                add(pool.random())
            }
            shuffle(Random.Default)
        }

        return result.joinToString("").take(length)
    }
}