package com.atm.olympuscourierapp.ui.features.DarConformidad.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.atm.olympuscourierapp.ui.components.CajaTexto
import com.atm.olympuscourierapp.ui.theme.ColorP1
import com.jjmf.android.olympuscourierapp.ui.features.DarConformidad.DarConformidadViewModel


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CardConformidadClaves(
    viewModel: DarConformidadViewModel,
    permiso: PermissionState,
) {
    val takePicture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = {
            viewModel.foto = it
        }
    )

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Datos de Conformidad",
                fontWeight = FontWeight.SemiBold,
                color = ColorP1
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {

                viewModel.reparto?.items?.forEachIndexed { i, item ->

                    CajaTexto(
                        valor = item.claveInsert.value,
                        newValor = { item.claveInsert.value = it },
                        titulo = "Guia: ${item.num_guia}",
                        label = "Ingrese clave ${i + 1}",
                        keyboardType = KeyboardType.Number,
                        visualTransformation = PasswordVisualTransformation()
                    )
                }


                if (viewModel.foto != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Image(
                            bitmap = viewModel.foto!!.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        IconButton(onClick = { viewModel.foto = null }) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                } else {
                    Button(
                        onClick = {
                            if (permiso.status.isGranted) {
                                takePicture.launch(null)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Adjuntar foto", color = Color.White)
                    }
                }

            }
        }
    }
}