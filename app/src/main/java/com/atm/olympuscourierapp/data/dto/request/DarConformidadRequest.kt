package com.atm.olympuscourierapp.data.dto.request

import com.google.gson.annotations.SerializedName

data class DarConformidadRequest(
    @SerializedName("id_reparto") val id_reparto:Int,
    @SerializedName("url_foto") val url_foto:String,
)