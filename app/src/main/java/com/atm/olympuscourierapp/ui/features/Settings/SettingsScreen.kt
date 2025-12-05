package com.atm.olympuscourierapp.ui.features.Settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.atm.olympuscourierapp.ui.theme.ColorR1
import com.atm.olympuscourierapp.ui.theme.ColorT

@Composable
fun SettingsScreen(
    back: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    BackHandler {
        back()
    }

    val isSeguro = remember { mutableStateOf(false) }

    if (viewModel.isSuccess.value) {
        AlertDialog(
            onDismissRequest = {

            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Cuenta eliminada con éxito.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Gracias por usar nuestra aplicación.",
                        fontSize = 14.sp
                    )
                }
            },
            confirmButton = {}
        )
    }

    if (isSeguro.value) {
        AlertDialog(
            onDismissRequest = {
                isSeguro.value = false
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "¿Estás seguro de que quieres eliminar tu cuenta?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Esta acción no se puede deshacer.",
                        fontSize = 14.sp
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isSeguro.value = false
                    }
                ) {
                    Text("Cancelar")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        isSeguro.value = false
                        viewModel.deleteAccount()
                    }
                ) {
                    Text("Confirmar")
                }
            }
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Configuración",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = ColorT
        )

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            if (viewModel.isLoading.value) {
                CircularProgressIndicator()
            } else {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    onClick = {
                        isSeguro.value = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorR1
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteOutline,
                        contentDescription = null
                    )
                    Text(
                        text = "Eliminar cuenta",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}