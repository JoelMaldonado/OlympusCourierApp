package com.atm.olympuscourierapp.ui.features.DetalleReparto

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.atm.olympuscourierapp.R
import com.atm.olympuscourierapp.ui.components.DetalleItem
import com.atm.olympuscourierapp.ui.theme.ColorP1
import com.atm.olympuscourierapp.ui.theme.ColorP2
import com.atm.olympuscourierapp.ui.theme.ColorWsp
import com.jjmf.android.olympuscourierapp.ui.features.DetalleReparto.DetailRepartoViewModel
import com.atm.olympuscourierapp.ui.features.DetalleReparto.components.CardItemReparto
import com.atm.olympuscourierapp.util.show

@Composable
fun DetailRepartoScreen(
    idReparto: Int,
    back: () -> Unit,
    viewModel: DetailRepartoViewModel = hiltViewModel(),
) {


    LaunchedEffect(key1 = Unit) {
        viewModel.getReparto(idReparto)
    }

    val context = LocalContext.current

    val reparto = viewModel.reparto ?: return


    if (viewModel.back) {
        LaunchedEffect(key1 = Unit) {
            viewModel.back = false
            back()
        }
    }

    if (viewModel.reparto == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }


    viewModel.error?.let {
        viewModel.error = null
        context.show(it)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = back) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = ColorP1
                )
            }
            Text(
                text = "Visualizar Reparto",
                fontWeight = FontWeight.SemiBold,
                color = ColorP1,
                fontSize = 22.sp
            )

            Text(
                text = reparto.estado.name,
                color = reparto.estado.color,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(reparto.estado.color.copy(0.1f))
                    .padding(horizontal = 5.dp, vertical = 3.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = ColorP1
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_caja),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Reparto #${reparto.formatoID()}",
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "S/${reparto.total()}",
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            DetalleItem(titulo = "Documento", descrip = reparto.cliente.documento)
            DetalleItem(titulo = "Cliente", descrip = reparto.cliente.nombres)
            DetalleItem(titulo = "Teléfono", descrip = reparto.cliente.formatTelefono())
            DetalleItem(titulo = "Distrito", descrip = reparto.cliente.distrito)
            DetalleItem(titulo = "Dirección", descrip = reparto.cliente.direc)
            DetalleItem(
                titulo = "Referencia",
                descrip = reparto.cliente.referencia.ifEmpty { "Sin Referencia" })
            DetalleItem(titulo = "Fecha", descrip = reparto.formatFecha())

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                FloatingActionButton(
                    onClick = {
                        try {
                            val mapIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(reparto.cliente.urlMaps))
                            context.startActivity(mapIntent)
                        } catch (e: Exception) {
                            Log.d("tagito", "${reparto.cliente.urlMaps}, Error: $e")
                        }
                    },
                    containerColor = ColorP2,
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Map,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                FloatingActionButton(
                    onClick = {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse("https://wa.me/${reparto.cliente.telefono}")
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            // Manejar excepciones según sea necesario
                        }
                    },
                    containerColor = ColorWsp,
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Whatsapp,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                FloatingActionButton(
                    onClick = {
                        val phoneNumber = "tel:${reparto.cliente.telefono}"
                        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
                        context.startActivity(dialIntent)
                    },
                    containerColor = ColorP1,
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            reparto.items.forEach { itemReparto ->
                CardItemReparto(
                    itemReparto = itemReparto
                )
            }
        }
    }
}