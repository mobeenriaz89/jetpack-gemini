package com.pral.jetpackgemini.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.pral.jetpackgemini.navigation.AppNavigation
import com.pral.jetpackgemini.ui.theme.JetpackGeminiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackGeminiTheme {
                AppNavigation()
            }
        }
    }
}