package com.atm.olympuscourierapp.ui.features.DetalleReparto.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.atm.olympuscourierapp.ui.theme.ColorP1
import com.atm.olympuscourierapp.ui.theme.ColorT1
import com.jjmf.android.olympuscourierapp.domain.model.ItemReparto


@Composable
fun CardItemReparto(
    itemReparto: ItemReparto,
) {

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = "N° Guia: ${itemReparto.num_guia.ifEmpty { "Sin Guia" }}",
                color = ColorP1,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = itemReparto.detalle.ifEmpty { "Sin Detalle" },
                color = ColorT1
            )

        }

    }
}