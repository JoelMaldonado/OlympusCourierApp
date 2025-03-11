package com.atm.olympuscourierapp.ui.features.VerRepartos

import com.atm.olympuscourierapp.domain.model.EstadoEnvio
import com.atm.olympuscourierapp.domain.model.Usuario
import com.jjmf.android.olympuscourierapp.domain.model.Distrito
import com.jjmf.android.olympuscourierapp.domain.model.Vehiculo
import java.util.Date

sealed class VerRepartoEvents {
    data class SetEstadoEnvio(val value: EstadoEnvio?) : VerRepartoEvents()
    data class SetDistrito(val value: Distrito?) : VerRepartoEvents()
    data class SetVehiculo(val value: Vehiculo?) : VerRepartoEvents()
    data class SetUsuario(val value: Usuario?) : VerRepartoEvents()
    data class SetFecha(val value: Date): VerRepartoEvents()
    data object ListarRepartos: VerRepartoEvents()
}