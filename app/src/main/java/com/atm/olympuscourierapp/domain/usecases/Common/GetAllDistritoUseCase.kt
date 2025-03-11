package com.atm.olympuscourierapp.domain.usecases.Common

import com.atm.olympuscourierapp.core.EstadosResult
import com.atm.olympuscourierapp.domain.repository.CommonRepository
import com.jjmf.android.olympuscourierapp.domain.model.Distrito
import javax.inject.Inject

class GetAllDistritoUseCase @Inject constructor(
    private val repo: CommonRepository
) {
    suspend operator fun invoke(): List<Distrito> {
        when(val res = repo.getAllDistritos()){
            is EstadosResult.Correcto -> return res.datos.orEmpty()
            is EstadosResult.Error -> throw Exception(res.mensajeError)
        }
    }
}