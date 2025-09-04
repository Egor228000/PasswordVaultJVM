package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import org.example.project.navigation.NavigationBottom
import org.example.project.viewModel.ViewModelPassword

class MainActivity : ComponentActivity() {
    private val viewModelPassword: ViewModelPassword by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val navControll = rememberNavController()

            NavigationBottom(navControll, viewModelPassword)
        }
    }
}

