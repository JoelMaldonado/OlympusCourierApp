package com.jjmf.android.olympuscourierapp.data.server.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.android.olympuscourierapp.domain.model.ItemReparto

data class ItemRepartoDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("num_guia") val num_guia: String?,
    @SerializedName("detalle") val detalle: String?,
    @SerializedName("adicional") val adicional: String?,
    @SerializedName("clave") val clave: String?,
    @SerializedName("precio") val precio: String?,
) {
    fun toDomain(): ItemReparto {
        return ItemReparto(
            id = id ?: 0,
            num_guia = num_guia ?: "Sin Data",
            detalle = detalle ?: "Sin Data",
            precio = precio ?: "Sin Data",
            clave = clave ?: "",
            adicional = adicional?.toDoubleOrNull() ?: 0.0
        )
    }
}
