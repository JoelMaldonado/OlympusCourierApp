package com.atm.olympuscourierapp.domain.repository

import com.atm.olympuscourierapp.core.EstadosResult
import com.atm.olympuscourierapp.domain.enum.EstadoEnvioEnum
import com.jjmf.android.olympuscourierapp.domain.model.NewReparto
import com.atm.olympuscourierapp.domain.model.Reparto

interface RepartoRepository {

    suspend fun listarRepartos(
        fecha: String? = null,
        estado: String? = null,
        id_distrito: Int? = null,
        id_vehiculo: Int? = null,
        id_subido: Int? = null
    ): EstadosResult<List<NewReparto>>

    suspend fun get(idReparto: Int): EstadosResult<Reparto>
    suspend fun subirMercaderia(idReparto: Int): EstadosResult<String>
    suspend fun darConformidad(idReparto: Int, urlFoto: String): EstadosResult<String>
    suspend fun cancelarReparto(idReparto: Int): EstadosResult<Nothing>
}