package com.pral.jetpackgemini.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    enabled : Boolean = true
) {
    ElevatedButton(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(0.5f)
    ) {
        if(!enabled) {
            CircularProgressIndicator()
            Spacer(Modifier.width(4.dp))
        }
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = fontSize,
        )
    }
}

@Preview
@Composable
fun AppButtonPreview() {

}