package com.atm.olympuscourierapp.ui.features.VerRepartos

import com.atm.olympuscourierapp.domain.model.EstadoEnvio
import com.atm.olympuscourierapp.domain.model.Usuario
import com.jjmf.android.olympuscourierapp.domain.model.Distrito
import com.jjmf.android.olympuscourierapp.domain.model.NewReparto
import com.jjmf.android.olympuscourierapp.domain.model.Vehiculo
import java.util.Date

data class VerRepartosState(
    val isLoading: Boolean = false,
    val listRepartos: List<NewReparto> = emptyList(),
    val listEstadoEnvio: List<EstadoEnvio> = emptyList(),
    val estadoFiltro: EstadoEnvio? = null,
    val listDistritos: List<Distrito> = emptyList(),
    val distritoFiltro: Distrito? = null,
    val listVehiculos: List<Vehiculo> = emptyList(),
    val vehiculoFiltro: Vehiculo? = null,
    var listDeliverys: List<Usuario> = emptyList(),
    val deliveryFiltro: Usuario? = null,
    val error: String? = null,
    val fecha: Date = Date()
)
