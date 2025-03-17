package com.pral.jetpackgemini.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pral.jetpackgemini.R

@Composable
fun AppInputField(
    text: String = "",
    label: String,
    keyboardType: KeyboardOptions = KeyboardOptions.Default,
    isPassword: Boolean = false,
    onTextChange: (String) -> Unit,
    icon: Int? = null,
    modifier: Modifier = Modifier
) {

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = { newText -> onTextChange(newText) },
        visualTransformation =
        if (isPassword && !passwordVisible) PasswordVisualTransformation()
        else VisualTransformation.None,
        label = {
            Text(
                text = label,
                modifier = modifier.padding(),
                fontSize = 16.sp
            )
        },
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else keyboardType,
        trailingIcon = {
            if (isPassword) {
                val ic = if (passwordVisible) R.drawable.visibility else R.drawable.visibility_off
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = painterResource(ic), contentDescription = "")
                }
            } else {
                icon?.let {
                    Icon(painter = painterResource(it), contentDescription = "")
                }
            }
        },
    )
}


@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    AppInputField(
        "",
        "label",
        isPassword = true,
        onTextChange = {},
        icon = R.drawable.visibility,
    )
}