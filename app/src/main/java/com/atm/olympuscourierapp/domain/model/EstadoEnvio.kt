package com.atm.olympuscourierapp.domain.model

import com.atm.olympuscourierapp.data.dto.EstadoEnvioDto

data class EstadoEnvio(
    val id: Int,
    val codigo: String,
    val descrip: String
) {
    fun toDto(): EstadoEnvioDto {
        return EstadoEnvioDto(
            id = id,
            codigo = codigo,
            descrip = descrip
        )
    }
}
