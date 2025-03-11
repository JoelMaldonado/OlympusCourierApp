package com.atm.olympuscourierapp.ui.features.VerCodigo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.atm.olympuscourierapp.R
import com.atm.olympuscourierapp.ui.theme.ColorS1
import com.jjmf.android.olympuscourierapp.ui.features.VerCodigo.MetodoPagoTab
import com.jjmf.android.olympuscourierapp.ui.features.VerCodigo.VerCodigoViewModel
import net.glxn.qrgen.android.QRCode

@Composable
fun VerCodigoScreen(
    back: () -> Unit,
    viewModel: VerCodigoViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = back) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = ColorS1
                )
            }
            Text(
                text = "Volver",
                color = ColorS1,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }


        val bitmap = when (viewModel.metodoPagoTab) {
            MetodoPagoTab.Yape -> QRCode.from(viewModel.metodoPagoTab.url).withSize(1024, 1024)
                .bitmap()

            MetodoPagoTab.Plin -> QRCode.from(viewModel.metodoPagoTab.url).withSize(1024, 1024)
                .bitmap()
        }


        val icon = when (viewModel.metodoPagoTab) {
            MetodoPagoTab.Yape -> R.drawable.yape
            MetodoPagoTab.Plin -> R.drawable.plin
        }
        Image(
            modifier = Modifier.width(80.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(if (viewModel.metodoPagoTab == MetodoPagoTab.Yape) MetodoPagoTab.Yape.color else MetodoPagoTab.Plin.color)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {


            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )

            Text(
                text = "JHONY ALONSO\nJALLORANA LOPEZ",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MetodoPagoTab.entries.forEach { metodoPago ->
                ElevatedFilterChip(
                    selected = viewModel.metodoPagoTab == metodoPago,
                    onClick = {
                        viewModel.metodoPagoTab = metodoPago
                    },
                    label = {
                        Text(
                            text = metodoPago.name,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    },
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        selectedContainerColor = metodoPago.color,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }
    }


}