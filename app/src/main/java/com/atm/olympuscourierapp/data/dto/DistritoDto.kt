package com.jjmf.android.olympuscourierapp.data.server.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.android.olympuscourierapp.domain.model.Distrito

data class DistritoDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("id_ruc") val id_ruc: Int?,
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("fecha_creacion") val fecha_creacion: String?,
    @SerializedName("activo") val activo: String?,
){
    fun toDomain(): Distrito {
        return Distrito(
            id = id ?: 0,
            id_ruc = id_ruc ?: 0,
            nombre = nombre ?: "",
            fecha_creacion = fecha_creacion ?: "",
            activo = activo ?: ""
        )
    }
}
