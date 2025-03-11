package com.atm.olympuscourierapp.ui.features.VerRepartos.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.atm.olympuscourierapp.ui.theme.ColorGrey
import com.atm.olympuscourierapp.ui.theme.ColorP1
import com.atm.olympuscourierapp.ui.theme.ColorT2


@Composable
fun FiltroItem(
    label: String,
    isSelected: Boolean,
    click: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp),
        colors = CardDefaults.cardColors(
            containerColor = ColorGrey,
            contentColor = if (isSelected) ColorP1 else ColorT2
        ),
        onClick = click
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null
            )
            Text(label)
        }
    }
}
