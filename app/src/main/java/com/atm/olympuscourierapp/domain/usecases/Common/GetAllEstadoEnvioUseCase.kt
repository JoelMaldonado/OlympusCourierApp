package com.atm.olympuscourierapp.domain.usecases.Common

import com.atm.olympuscourierapp.core.EstadosResult
import com.atm.olympuscourierapp.domain.model.EstadoEnvio
import com.atm.olympuscourierapp.domain.repository.CommonRepository
import javax.inject.Inject

class GetAllEstadoEnvioUseCase @Inject constructor(
    private val repo: CommonRepository
) {
    suspend operator fun invoke(): List<EstadoEnvio> {
        return when (val res = repo.getAllEstadoEnvios()) {
            is EstadosResult.Correcto -> res.datos.orEmpty()
            is EstadosResult.Error -> throw Exception(res.mensajeError)
        }
    }
}