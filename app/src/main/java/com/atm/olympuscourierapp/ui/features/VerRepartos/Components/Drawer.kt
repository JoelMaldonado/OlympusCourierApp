package com.atm.olympuscourierapp.ui.features.VerRepartos.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atm.olympuscourierapp.ui.components.FiltroGenerico
import com.atm.olympuscourierapp.ui.features.VerRepartos.VerRepartoEvents
import com.atm.olympuscourierapp.ui.features.VerRepartos.VerRepartosState
import com.atm.olympuscourierapp.ui.theme.ColorP1
import com.atm.olympuscourierapp.ui.theme.ColorT1

@Composable
fun Drawer(
    state: VerRepartosState,
    setEvent: (VerRepartoEvents) -> Unit,
    dismiss: () -> Unit,
    filtrar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .fillMaxHeight()
            .background(Color.White)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Filtros",
                fontWeight = FontWeight.Black,
                color = ColorP1
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Filtra por estado de asignación, fechas y orden de prioridad.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = ColorT1,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            FiltroGenerico(
                title = "Tipo Estado",
                value = state.estadoFiltro,
                newValue = { newEstado ->
                    setEvent(VerRepartoEvents.SetEstadoEnvio(newEstado))
                },
                list = state.listEstadoEnvio,
                labelProvider = { it.descrip }
            )

            FiltroGenerico(
                title = "Distrito",
                value = state.distritoFiltro,
                newValue = { newDistrito ->
                    setEvent(VerRepartoEvents.SetDistrito(newDistrito))
                },
                list = state.listDistritos,
                labelProvider = { it.nombre }
            )

            FiltroGenerico(
                title = "Vehiculo",
                value = state.vehiculoFiltro,
                newValue = { newVehiculo ->
                    setEvent(VerRepartoEvents.SetVehiculo(newVehiculo))
                },
                list = state.listVehiculos,
                labelProvider = { it.nombre }
            )

            FiltroGenerico(
                title = "Distribuidor",
                value = state.deliveryFiltro,
                newValue = { newUsuario ->
                    setEvent(VerRepartoEvents.SetUsuario(newUsuario))
                },
                list = state.listDeliverys,
                labelProvider = { it.nombres }
            )

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextButton(
                onClick = dismiss
            ) {
                Text("Cerrar")
            }
            Button(
                onClick = {
                    dismiss()
                    filtrar()
                }
            ) {
                Text("Aplicar")
            }
        }
    }
}