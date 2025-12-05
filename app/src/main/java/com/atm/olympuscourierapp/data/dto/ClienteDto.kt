package com.atm.olympuscourierapp.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.android.olympuscourierapp.domain.model.Cliente

data class ClienteDto(
    @SerializedName("tipo_doc") val tipoDoc: String?,
    @SerializedName("documento") val documento: String?,
    @SerializedName("nombres") val nombres: String?,
    @SerializedName("telefono") val telefono: String?,
    @SerializedName("correo") val correo: String?,
    @SerializedName("genero") val genero: String?,
    @SerializedName("id_distrito") val idDistrito: Int?,
    @SerializedName("distrito") val distrito: String?,
    @SerializedName("direc") val direc: String?,
    @SerializedName("referencia") val referencia: String?,
    @SerializedName("url_maps") val urlMaps: String?,
) {
    fun toDomain(): Cliente {
        return Cliente(
            tipoDoc = tipoDoc ?: "Sin Valor",
            documento = documento ?: "Sin Valor",
            nombres = nombres ?: "Sin Valor",
            telefono = telefono ?: "Sin Valor",
            correo = correo ?: "Sin Valor",
            genero = genero ?: "Sin Valor",
            idDistrito = idDistrito ?: 0,
            distrito = distrito ?: "Sin Valor",
            direc = direc ?: "Sin Valor",
            referencia = referencia ?: "Sin Valor",
            urlMaps = urlMaps ?: "Sin Valor"
        )
    }
}