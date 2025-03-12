package com.pral.jetpackgemini.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pral.jetpackgemini.api.PromptViewModel
import com.pral.jetpackgemini.api.UiState
import com.pral.jetpackgemini.ui.theme.JetpackGeminiTheme
import com.pral.jetpackgemini.views.AppButton
import com.pral.jetpackgemini.views.AppInputField
import com.pral.jetpackgemini.views.AppTextView
import com.pral.jetpackgemini.views.ResultDialog

class MainActivity : ComponentActivity() {

    private var vm = PromptViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackGeminiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(modifier = Modifier.padding(innerPadding), vm)
                }
            }
        }
    }
}

@Composable
fun MainView(modifier: Modifier = Modifier, vm: PromptViewModel = viewModel()) {

    val uiState by vm.uiState.collectAsState()

    var age by remember { mutableStateOf("36") }
    var gender by remember { mutableStateOf("Male") }
    var height by remember { mutableStateOf("5 ft 6 inch") }
    var weight by remember { mutableStateOf("65") }
    var result by rememberSaveable { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            AppTextView(text = "Provide Your Info", fontSize = 22.sp)
            Spacer(Modifier.height(24.dp))

            AppInputField(
                label = "Age",
                text = age,
                onTextChange = { age = it },
                keyboardType = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(Modifier.height(12.dp))
            AppInputField(label = "Gender", text = gender, onTextChange = { gender = it })
            Spacer(Modifier.height(12.dp))
            AppInputField(label = "Height", text = height, onTextChange = { height = it })
            Spacer(Modifier.height(12.dp))
            AppInputField(label = "Weight", text = weight, onTextChange = { weight = it })

            Spacer(Modifier.height(24.dp))
            AppButton(
                "Proceed", {
                    val query = "Provide a diet plan for following info:" +
                            " Age: $age, " +
                            " Gender: $gender, " +
                            " Weight: $weight, " +
                            " Height: $height "
                    vm.sendPrompt(query)
                },
                enabled = uiState is UiState.Initial
            )
        }

        /*if (uiState is UiState.Loading) {
            CircularProgressIndicator(modifier.align(Alignment.Center))
        } else {*/
            if (uiState is UiState.Error) {
                result = (uiState as UiState.Error).errorMessage
                showDialog = true
            } else if (uiState is UiState.Success) {
                result = (uiState as UiState.Success).outputText
                showDialog = true
            }
        //}
        ResultDialog(
            text = result,
            showDialog = showDialog,
            onDismiss = {
                showDialog = false
                vm.resetState()
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainViewPreview() {
    JetpackGeminiTheme {
        Scaffold(Modifier.fillMaxSize()) {
            MainView(modifier = Modifier.padding(it))
        }
    }

}