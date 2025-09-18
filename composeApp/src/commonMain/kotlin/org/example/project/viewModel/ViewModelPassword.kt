package org.example.project.viewModel

import androidx.lifecycle.ViewModel
import kotlin.math.pow
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
    fun estimateCrackTime(password: String): String {
        if (password.isEmpty()) return "Мгновенно"

        var hasDigits = false
        var hasLower = false
        var hasUpper = false
        var hasSpecial = false

        for (char in password) {
            when {
                char.isDigit() -> hasDigits = true
                char.isLowerCase() -> hasLower = true
                char.isUpperCase() -> hasUpper = true
                else -> hasSpecial = true
            }
        }

        val charsetSize = when {
            hasSpecial -> 80
            hasUpper && hasLower -> 62
            hasUpper || hasLower -> 36
            hasDigits -> 10
            else -> 26
        }

        val combinations = charsetSize.toDouble().pow(password.length.toDouble())

        val attemptsPerSecond = 1e12
        val seconds = combinations / attemptsPerSecond

        return when {
            seconds < 1 -> "менее секунды"
            seconds < 60 -> "${seconds.toInt()} секунд"
            seconds < 3600 -> "${(seconds / 60).toInt()} минут"
            seconds < 86400 -> "${(seconds / 3600).toInt()} часов"
            seconds < 31536000 -> "${(seconds / 86400).toInt()} дней"
            seconds < 3.15576e16 -> "${(seconds / 3.15576e7).toInt()} лет"
            else -> "Миллионы лет"
        }
    }
}