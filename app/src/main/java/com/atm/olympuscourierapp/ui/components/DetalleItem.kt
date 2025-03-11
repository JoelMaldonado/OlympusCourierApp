package com.atm.olympuscourierapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atm.olympuscourierapp.ui.theme.ColorBox
import com.atm.olympuscourierapp.ui.theme.ColorT
import com.atm.olympuscourierapp.ui.theme.ColorT1

@Composable
fun DetalleItem(titulo: String, descrip: String, color: Color = ColorT) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = titulo,
            fontWeight = FontWeight.Medium,
            color = color,
            fontSize = 14.sp
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(ColorBox)
                .padding(10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = descrip.uppercase(), color = ColorT1, fontSize = 14.sp)
        }
    }
}