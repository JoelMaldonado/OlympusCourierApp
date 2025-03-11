package com.jjmf.android.olympuscourierapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.atm.olympuscourierapp.ui.theme.ColorP1

@Composable
fun BigText(text: String, modifier: Modifier = Modifier, color: Color = ColorP1) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = color,
        fontSize = 28.sp,
        modifier = modifier
    )
}