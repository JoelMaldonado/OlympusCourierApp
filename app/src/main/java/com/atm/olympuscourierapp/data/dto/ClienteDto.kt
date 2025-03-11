package com.atm.olympuscourierapp.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.android.olympuscourierapp.domain.model.Cliente

data class ClienteDto(
    @SerializedName("tipo_doc") val tipo_doc: String?,
    @SerializedName("documento") val documento: String?,
    @SerializedName("nombres") val nombres: String?,
    @SerializedName("telefono") val telefono: String?,
    @SerializedName("correo") val correo: String?,
    @SerializedName("genero") val genero: String?,
    @SerializedName("id_distrito") val id_distrito: Int?,
    @SerializedName("distrito") val distrito: String?,
    @SerializedName("direc") val direc: String?,
    @SerializedName("referencia") val referencia: String?,
    @SerializedName("url_maps") val url_maps: String?,
) {
    fun toDomain(): Cliente {
        return Cliente(
            tipo_doc = tipo_doc ?: "Sin Valor",
            documento = documento ?: "Sin Valor",
            nombres = nombres ?: "Sin Valor",
            telefono = telefono ?: "Sin Valor",
            correo = correo ?: "Sin Valor",
            genero = genero ?: "Sin Valor",
            id_distrito = id_distrito ?: 0,
            distrito = distrito ?: "Sin Valor",
            direc = direc ?: "Sin Valor",
            referencia = referencia ?: "Sin Valor",
            urlMaps = url_maps ?: "Sin Valor"
        )
    }
}