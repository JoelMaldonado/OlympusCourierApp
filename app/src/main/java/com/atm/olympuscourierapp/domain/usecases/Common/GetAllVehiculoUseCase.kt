package com.atm.olympuscourierapp.domain.usecases.Common

import com.atm.olympuscourierapp.core.EstadosResult
import com.atm.olympuscourierapp.domain.repository.CommonRepository
import com.jjmf.android.olympuscourierapp.domain.model.Vehiculo
import javax.inject.Inject

class GetAllVehiculoUseCase @Inject constructor(
    private val repo: CommonRepository
) {
    suspend operator fun invoke(): List<Vehiculo> {
        when(val res = repo.getAllVehiculos()){
            is EstadosResult.Correcto -> return res.datos.orEmpty()
            is EstadosResult.Error -> throw Exception(res.mensajeError)
        }
    }
}