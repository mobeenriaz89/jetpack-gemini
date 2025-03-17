package com.pral.jetpackgemini.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pral.jetpackgemini.api.PromptViewModel
import com.pral.jetpackgemini.api.UiState
import com.pral.jetpackgemini.ui.components.AppButton
import com.pral.jetpackgemini.ui.components.AppInputField
import com.pral.jetpackgemini.ui.components.AppTextView

@Composable
fun MainScreen(modifier: Modifier = Modifier, vm: PromptViewModel = viewModel()) {

    val uiState by vm.uiState.collectAsState()

    var age by remember { mutableStateOf("36") }
    var gender by remember { mutableStateOf("Male") }
    var height by remember { mutableStateOf("5 ft 6 inch") }
    var weight by remember { mutableStateOf("65") }
    var result by remember { mutableStateOf("") }
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
                "Proceed",
               onClick =  {
                    val query = "Provide a diet plan for following info:" +
                            " Age: $age, " +
                            " Gender: $gender, " +
                            " Weight: $weight, " +
                            " Height: $height "
                    vm.sendPrompt(query)
                },
                enabled = uiState is UiState.Initial,
            )
        }

        if (uiState is UiState.Error) {
            result = (uiState as UiState.Error).errorMessage
            showDialog = true
        } else if (uiState is UiState.Success) {
            result = (uiState as UiState.Success).outputText
            showDialog = true
        }

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

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}
