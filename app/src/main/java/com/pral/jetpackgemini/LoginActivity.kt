package com.pral.jetpackgemini

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pral.jetpackgemini.ui.theme.JetpackGeminiTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackGeminiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Only apply innerPadding here, as it's already adjusted for system UI elements
                    LoginView(
                        title = "Gemini App",
                        modifier = Modifier.padding(innerPadding) // No need for statusBarsPadding()
                    )
                }
            }
        }

    }
}

@Composable
fun LoginView(title: String, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            modifier = modifier.align(Alignment.CenterHorizontally),
            fontSize = 34.sp
        )

        Text(
            text = "Login",
            fontFamily = FontFamily.Monospace,
            color = Color.White,
            modifier = modifier.align(Alignment.CenterHorizontally),
            fontSize = 26.sp
        )
        InputView()
        Spacer(Modifier.height(24.dp))
        val context = LocalContext.current

        ElevatedButton(
            onClick = {
                val i = Intent(context,MainActivity::class.java)
                context.startActivity(i)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            modifier = Modifier.padding(8.dp).fillMaxWidth(0.5f)
        ) {
            Text(
                text = "Login",
                color = MaterialTheme.colorScheme.onSecondary,
                fontFamily = FontFamily.Cursive,
                fontSize = 24.sp,
            )
        }


    }
}

@Composable
fun login() {


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
            icon = Icons.Filled.AccountCircle
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


@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetpackGeminiTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            // Only apply innerPadding here, as it's already adjusted for system UI elements
            LoginView(
                title = "Gemini app",
                modifier = Modifier.padding(innerPadding) // No need for statusBarsPadding()
            )
        }
    }
}