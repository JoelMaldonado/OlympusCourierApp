package com.atm.olympuscourierapp.data.dto

import com.atm.olympuscourierapp.domain.model.EstadoEnvio
import com.google.gson.annotations.SerializedName

data class EstadoEnvioDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("codigo") val codigo: String?,
    @SerializedName("descripcion") val descrip: String?,
) {
    fun toDomain(): EstadoEnvio {
        return EstadoEnvio(
            id = id ?: throw IllegalArgumentException("ID no puede ser nulo"),
            codigo = codigo ?: "",
            descrip = descrip ?: ""
        )
    }
}
