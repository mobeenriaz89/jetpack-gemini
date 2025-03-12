package com.pral.jetpackgemini.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultDialog(text: String, showDialog: Boolean, onDismiss: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest =  onDismiss,
            title = { Text("Your diet plan") },
            text = {
                Box(modifier = Modifier.height(200.dp)) { // Limit height for scrolling
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        Text(
                            text = text, // Long text
                            fontSize = 16.sp
                        )
                    }
                }
            },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("Dismiss")
                }
            }
        )
    }
}


@Preview
@Composable
fun ResultDialogPrev() {
    ResultDialog(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ".repeat(
        20
    ), true,{})
}