package com.atm.olympuscourierapp.ui.features.VerRepartos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.atm.olympuscourierapp.R
import com.atm.olympuscourierapp.ui.components.CardReparto
import com.atm.olympuscourierapp.ui.components.CardRepartoShimmer
import com.atm.olympuscourierapp.ui.features.VerRepartos.Components.Drawer
import com.atm.olympuscourierapp.ui.features.VerRepartos.Components.TopRepartos
import com.atm.olympuscourierapp.ui.theme.ColorP1
import kotlinx.coroutines.launch

@Composable
fun VerRepartosScreen(
    toPerfil: () -> Unit,
    toDetalle: (Int) -> Unit,
    toDarConformidad: (Int) -> Unit,
    viewModel: VerRepartosViewModel = hiltViewModel()
) {

    val filtroDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutine = rememberCoroutineScope()

    val state = viewModel.state

    LaunchedEffect(
        key1 = Unit
    ) {
        viewModel.setEvent(VerRepartoEvents.ListarRepartos)
        viewModel.init()
    }

    ModalNavigationDrawer(
        drawerState = filtroDrawerState,
        drawerContent = {
            Drawer(
                state = state,
                setEvent = { event ->
                    viewModel.setEvent(event)
                },
                dismiss = {
                    coroutine.launch {
                        filtroDrawerState.close()
                    }
                },
                filtrar = {
                    viewModel.setEvent(VerRepartoEvents.ListarRepartos)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopRepartos(
                toPerfil = toPerfil,
                state = state,
                setEvent = { event ->
                    viewModel.setEvent(event)
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp, vertical = 5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total: (${state.listRepartos.size})",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    IconButton(
                        onClick = {
                            coroutine.launch {
                                filtroDrawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filtro),
                            contentDescription = null,
                            tint = ColorP1,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), contentAlignment = Alignment.Center
                ) {
                    when {
                        state.isLoading -> LazyColumn {
                            items(6) {
                                CardRepartoShimmer()
                            }
                        }
                        state.listRepartos.isEmpty() -> Text(
                            text = "No hay repartos para mostrar",
                            color = Color.Gray
                        )

                        else -> {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(state.listRepartos) { reparto ->
                                    val id = reparto.id
                                    CardReparto(
                                        reparto = reparto,
                                        toDetalle = {
                                            toDetalle(id)
                                        },
                                        subirMercaderia = {
                                            viewModel.subirMercaderia(id)
                                        },
                                        toDarConformidad = {
                                            toDarConformidad(id)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}