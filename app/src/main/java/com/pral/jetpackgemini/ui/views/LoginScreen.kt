package com.pral.jetpackgemini.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pral.jetpackgemini.R
import com.pral.jetpackgemini.navigation.NavigationItem
import com.pral.jetpackgemini.ui.components.AppButton
import com.pral.jetpackgemini.ui.components.AppInputField
import com.pral.jetpackgemini.ui.components.AppTextView

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = Color.Black,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    color = Color.White,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 34.sp
                )
            }
        }
        AppTextView(
            "Login",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 12.dp),
            fontSize = 38.sp
        )
        InputView()
        Spacer(Modifier.height(24.dp))

        AppButton("Login", onClick = {
            navController.navigate(NavigationItem.Home.route) {
                popUpTo(NavigationItem.Login.route) { inclusive = true }
            }
        })
    }
}

@Composable
fun InputView(modifier: Modifier = Modifier) {

    var email by remember { mutableStateOf("") } // Use mutableStateOf instead of mutableSetOf
    var password by remember { mutableStateOf("") } // Use mutableStateOf instead of mutableSetOf

    Column {
        AppInputField(
            text = email,
            label = "Email",
            onTextChange = { email = it },
            icon = R.drawable.account
        )
        Spacer(modifier.height(12.dp))
        AppInputField(
            text = password,
            label = "Password",
            isPassword = true,
            onTextChange = { password = it }
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen(rememberNavController())
}