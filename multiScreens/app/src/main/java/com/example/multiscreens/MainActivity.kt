package com.example.multiscreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.multiscreens.ui.theme.MultiScreensTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiScreensTheme {
                val navController = rememberNavController()
                val screens: Screens = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "main",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("main") {
                            screens.MainScreen(navController)
                        }
                        composable("second") {
                            screens.SecondScreen(navController)
                        }
                        composable("last") {
                            screens.LastScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
