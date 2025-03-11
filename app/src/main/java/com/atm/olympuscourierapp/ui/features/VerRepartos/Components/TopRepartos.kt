package com.atm.olympuscourierapp.ui.features.VerRepartos.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atm.olympuscourierapp.ui.features.VerRepartos.VerRepartoEvents
import com.atm.olympuscourierapp.ui.features.VerRepartos.VerRepartosState
import com.atm.olympuscourierapp.ui.theme.ColorP1
import com.atm.olympuscourierapp.util.format
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopRepartos(
    toPerfil: () -> Unit,
    state: VerRepartosState,
    setEvent: (VerRepartoEvents) -> Unit
) {

    val bool = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )

    if (bool.value) {
        DatePickerDialog(
            onDismissRequest = {
                bool.value = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        bool.value = false
                        datePickerState.selectedDateMillis?.let { millis ->
                            val fecha = Date(millis)
                            setEvent(VerRepartoEvents.SetFecha(fecha))
                            setEvent(VerRepartoEvents.ListarRepartos)
                        }
                    }
                ) {
                    Text("Guardar")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = false
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp, RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
            .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
            .background(ColorP1)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Titulo
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Lista de Repartos",
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            IconButton(
                onClick = toPerfil,
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Default.ManageAccounts,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = ColorP1
            ),
            shape = CircleShape,
            onClick = {
                bool.value = true
            }
        ) {
            Row(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val fecha = state.fecha.format("dd/MM/yyyy")
                Text(text = fecha, fontWeight = FontWeight.Medium)
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = null
                )
            }
        }
    }
}
