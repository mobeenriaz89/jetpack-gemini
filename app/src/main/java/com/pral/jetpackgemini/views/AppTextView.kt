package com.pral.jetpackgemini.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun AppTextView(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = text,
        color = Color.White,
        modifier = modifier,
        fontSize = fontSize
    )
}

@Preview
@Composable
fun AppTextViewPreview() {
    AppTextView("Provide Your Info")
}