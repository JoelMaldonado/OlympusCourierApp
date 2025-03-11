package com.jjmf.android.olympuscourierapp.ui.features.VerRepartos.Components

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogSelectFecha(
    state: DatePickerState,
    close: ()->Unit,
    click: ()->Unit
) {
    DatePickerDialog(
        onDismissRequest = close,
        confirmButton = {
            Button(
                onClick = {
                    close()
                    click()
                }
            ) {
                Text(text = "Confirmar")
            }
        }
    ) {
        DatePicker(state = state)
    }
}