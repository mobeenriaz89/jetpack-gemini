package com.pral.jetpackgemini.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pral.jetpackgemini.R
import com.pral.jetpackgemini.ui.theme.JetpackGeminiTheme
import com.pral.jetpackgemini.views.AppButton
import com.pral.jetpackgemini.views.AppInputField
import com.pral.jetpackgemini.views.AppTextView

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
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 24.dp),
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
                    text = title,
                    color = Color.White,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 34.sp
                )
            }
        }
        AppTextView(
            "Login",
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 12.dp),
            fontSize = 38.sp
        )
        InputView()
        Spacer(Modifier.height(24.dp))

        val context = LocalContext.current
        AppButton("Login", onClick = {
                login(context)
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

fun login(context: Context) {
    val i = Intent(context, MainActivity::class.java)
    context.startActivity(i)
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