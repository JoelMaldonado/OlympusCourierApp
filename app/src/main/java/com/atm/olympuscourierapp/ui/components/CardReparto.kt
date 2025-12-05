package com.atm.olympuscourierapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Unarchive
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.pedant.SweetAlert.SweetAlertDialog
import com.atm.olympuscourierapp.R
import com.atm.olympuscourierapp.domain.model.EstadoReparto
import com.atm.olympuscourierapp.ui.theme.ColorP1
import com.atm.olympuscourierapp.ui.theme.ColorP2
import com.atm.olympuscourierapp.ui.theme.ColorS1
import com.atm.olympuscourierapp.ui.theme.ColorSec
import com.atm.olympuscourierapp.ui.theme.ColorT1
import com.jjmf.android.olympuscourierapp.domain.model.NewReparto

@Composable
fun CardReparto(
    id: String,
    cliente: String,
    direccion: String,
    estado: EstadoReparto,
    toDetalle: () -> Unit,
    subirMercaderia: () -> Unit,
    toDarConformidad: () -> Unit,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(10.dp),
            onClick = toDetalle
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_caja),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )

                        Text(
                            text = id,
                            color = ColorP1,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                fontSize = 14.sp,
                                text = cliente,
                                color = ColorT1
                            )
                            Text(
                                fontSize = 12.sp,
                                text = direccion,
                                color = ColorT1,
                                lineHeight = 16.sp
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = ColorP2
                        )
                    }
                }

                Text(
                    text = estado.nombre,
                    color = estado.color,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 10.dp, end = 10.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(estado.color.copy(0.1f))
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
        }
        when (estado) {
            EstadoReparto.Pendiente -> {
                ButtonIcon(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
                            titleText = "Subir Mercaderia"
                            contentText = "¿Estas seguro de subir la mercaderia?"
                            confirmText = "Si"
                            cancelText = "No"
                            confirmButtonBackgroundColor = ColorP1.hashCode()
                            cancelButtonBackgroundColor = ColorS1.hashCode()
                            setConfirmClickListener {
                                dismissWithAnimation()
                                subirMercaderia()
                            }
                            setCancelClickListener {
                                dismissWithAnimation()
                            }
                            show()
                        }
                    },
                    icon = Icons.Outlined.Unarchive,
                    label = "Subir Mercaderia",
                    containerColor = ColorSec
                )
            }

            EstadoReparto.EnCurso -> {
                ButtonIcon(
                    modifier = Modifier.align(Alignment.End),
                    onClick = toDarConformidad,
                    icon = Icons.Default.Assignment,
                    label = "Entregar"
                )
            }

            EstadoReparto.Entregado -> {}
            EstadoReparto.Anulado -> {}
        }
    }
}

