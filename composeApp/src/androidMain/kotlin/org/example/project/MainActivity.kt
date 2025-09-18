package org.example.project

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.fragment.app.FragmentActivity
import androidx.navigation3.runtime.NavKey
import io.github.kdroidfilter.platformtools.darkmodedetector.isSystemInDarkMode
import org.example.project.Theme.DarkColors
import org.example.project.Theme.LightColors
import org.example.project.Theme.MyAppTheme
import org.example.project.navigation.NavigationBottom
import org.example.project.navigation.SplashPassword
import org.example.project.viewModel.ViewModelPassword

class MainActivity : FragmentActivity() {
    private val viewModelPassword: ViewModelPassword by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        initDatabase(this)

        setContent {
            val backStack = remember { mutableStateListOf<NavKey>(SplashPassword) }

            MyAppTheme(
                colorScheme = if (isSystemInDarkMode()) DarkColors else LightColors
            ) {
                NavigationBottom(backStack, viewModelPassword)

            }
        }
    }
}


