package com.pral.jetpackgemini.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pral.jetpackgemini.R

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    enabled: Boolean = true,
    bgColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
        var btnText by remember { mutableStateOf(text) }
        val animatedColor by animateColorAsState(
            targetValue = if(enabled) bgColor else MaterialTheme.colorScheme.secondaryContainer,
            animationSpec = tween(2000),
            label = "Bg color animation"
        )
    
    val animatedPadding by animateDpAsState(
        if(enabled) 8.dp else 32.dp,
        animationSpec = tween(3000)
    )

    ElevatedButton(
        onClick = { if (enabled) onClick() },
        //enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = animatedColor
        ),
        modifier = modifier.wrapContentWidth(),
    ) {
        if(!enabled) {
            CircularProgressIndicator()
            Spacer(Modifier.width(8.dp))
            btnText = "Processing"
        }else{
            btnText = text
        }
        Text(
            text = btnText,
            fontSize = fontSize,
            modifier = Modifier.padding(animatedPadding)
        )
    }
}

@Preview
@Composable
fun AppButtonPreview() {
    AppButton("Button",{})
}