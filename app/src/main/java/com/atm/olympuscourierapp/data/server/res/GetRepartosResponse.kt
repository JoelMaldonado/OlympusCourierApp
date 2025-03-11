package com.atm.olympuscourierapp.data.server.res

import com.google.gson.annotations.SerializedName
import com.jjmf.android.olympuscourierapp.data.server.dto.NewRepartoDto

data class GetRepartosResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("data") val data: List<NewRepartoDto>
)
