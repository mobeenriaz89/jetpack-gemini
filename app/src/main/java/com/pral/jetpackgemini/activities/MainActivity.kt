package com.pral.jetpackgemini.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pral.jetpackgemini.ui.theme.JetpackGeminiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackGeminiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainView(modifier: Modifier = Modifier) {
    Text(
        text = "Dashboard",
        modifier = modifier.padding(22.dp),
        fontSize = 22.sp,
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainViewPreview() {
    JetpackGeminiTheme {
        Scaffold(Modifier.fillMaxSize()) {
            MainView( modifier = Modifier.padding(it))
        }
    }

}