package com.pral.jetpackgemini.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pral.jetpackgemini.R
import com.pral.jetpackgemini.navigation.NavigationItem
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val isVisible = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(1000) // Delay for 3 seconds
        isVisible.value = false
        delay(500)
        navController.navigate(NavigationItem.Login.route) {
            popUpTo(NavigationItem.Splash.route) { inclusive = true } // Removes splash from backstack
        }
    }

    AnimatedVisibility(visible = isVisible.value,
        enter = fadeIn(animationSpec = tween(3000)) + scaleIn(initialScale = 0.8f),
        exit = fadeOut(animationSpec = tween(3000)) + scaleOut(targetScale = 0.8f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(150.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}
