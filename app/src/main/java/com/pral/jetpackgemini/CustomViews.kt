package com.pral.jetpackgemini

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppInputField(
    text: String,
    label: String,
    isPassword: Boolean = false,
    onTextChange: (String) -> Unit,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
) {

    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = text,
        onValueChange = { newText -> onTextChange(newText) },
        visualTransformation =
        if (isPassword) PasswordVisualTransformation()
        else VisualTransformation.None,
        label = {
            Text(
                text = label,
                fontFamily = FontFamily.Monospace,
                modifier = modifier.padding()
            )
        },
        trailingIcon = {
            icon?.let {
                    Icon(imageVector = it, contentDescription = "Toggle Password Visibility")
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    AppInputField("text", "label",
        isPassword = true,
        {},
        Icons.Filled.AccountCircle)
}