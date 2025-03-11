package com.atm.olympuscourierapp.ui.features.DarConformidad

import android.Manifest
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import cn.pedant.SweetAlert.SweetAlertDialog
import com.atm.olympuscourierapp.R
import com.atm.olympuscourierapp.ui.theme.ColorP1
import com.atm.olympuscourierapp.ui.theme.ColorP2
import com.atm.olympuscourierapp.ui.theme.ColorS1
import com.atm.olympuscourierapp.ui.theme.ColorT
import com.atm.olympuscourierapp.ui.theme.ColorT1
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.jjmf.android.olympuscourierapp.ui.features.DarConformidad.DarConformidadViewModel
import com.atm.olympuscourierapp.ui.features.DarConformidad.components.CardConformidad
import com.atm.olympuscourierapp.ui.features.DarConformidad.components.CardConformidadClaves
import com.atm.olympuscourierapp.util.show


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DarConformidadScreen(
    idReparto: Int,
    back: () -> Unit,
    viewModel: DarConformidadViewModel = hiltViewModel(),
) {

    val permiso = rememberPermissionState(permission = Manifest.permission.CAMERA)


    LaunchedEffect(key1 = Unit) {
        viewModel.getReparto(idReparto)
        permiso.launchPermissionRequest()
    }

    val context = LocalContext.current

    val reparto = viewModel.reparto ?: return

    if (viewModel.back) {
        LaunchedEffect(key1 = Unit) {
            viewModel.back = false
            back()
        }
    }

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }

    if (viewModel.reparto == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            IconButton(onClick = back) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = ColorP1
                )
            }
            Text(
                text = "Conformidad de Entrega",
                color = ColorP1,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ColorP1)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_caja),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Text(
                    text = "Reparto N°${reparto.formatoID()}",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }

            CardConformidad(reparto = reparto)

            CardConformidadClaves(
                viewModel = viewModel,
                permiso = permiso
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
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Detalle",
                        fontWeight = FontWeight.SemiBold,
                        color = ColorP1
                    )
                    reparto.items.forEach {
                        Text(text = "· ${it.detalle}")
                    }
                }
            }

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
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Cobros",
                        fontWeight = FontWeight.SemiBold,
                        color = ColorP1
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Adicional",
                            color = ColorT1,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = "S/${reparto.totalAdicional()}",
                            color = ColorT,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Reparto",
                            color = ColorT1,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = "S/${reparto.total()}",
                            color = ColorT,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Total",
                            color = ColorT1,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = "S/${reparto.total() + reparto.totalAdicional()}",
                            color = ColorT,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator()
                } else {
                    TextButton(
                        onClick = {
                            SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
                                titleText = "Cancelar Entrega"
                                contentText = "¿Estas seguro de cancelar la mercaderia?"
                                confirmText = "Si"
                                cancelText = "No"
                                confirmButtonBackgroundColor = ColorP1.hashCode()
                                cancelButtonBackgroundColor = ColorS1.hashCode()
                                setConfirmClickListener {
                                    dismissWithAnimation()
                                    viewModel.cancelar(idReparto)
                                }
                                setCancelClickListener {
                                    dismissWithAnimation()
                                }
                                show()
                            }
                        }
                    ) {
                        Text(text = "Cancelar Entrega")
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.confirmarEntrega(idReparto)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorP2,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Confirmar Entrega")
                    }
                }
            }
        }
    }

}