package com.atm.olympuscourierapp.domain.repository

import com.atm.olympuscourierapp.core.EstadosResult
import com.atm.olympuscourierapp.domain.model.EstadoEnvio
import com.jjmf.android.olympuscourierapp.domain.model.Distrito
import com.jjmf.android.olympuscourierapp.domain.model.Vehiculo

interface CommonRepository {
    suspend fun getAllEstadoEnvios(): EstadosResult<List<EstadoEnvio>>
    suspend fun getAllDistritos(): EstadosResult<List<Distrito>>
    suspend fun getAllVehiculos() : EstadosResult<List<Vehiculo>>
}