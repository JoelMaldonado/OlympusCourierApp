package com.jjmf.android.olympuscourierapp.data.server.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.android.olympuscourierapp.domain.model.Vehiculo

data class VehiculoDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("id_ruc") val id_ruc: Int?,
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("fecha_creacion") val fecha_creacion:String?,
    @SerializedName("activo") val activo:String?
){
    fun toDomain() : Vehiculo {
        return Vehiculo(
            id = id ?: 0,
            id_ruc = id_ruc ?: 0,
            nombre = nombre ?: "Sin Nombre",
            fecha_creacion = fecha_creacion ?: "1999-01-01",
            activo = activo ?: "N"
        )
    }
}
