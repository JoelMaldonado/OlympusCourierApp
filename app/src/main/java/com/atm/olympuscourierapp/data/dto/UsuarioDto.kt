package com.jjmf.android.olympuscourierapp.data.server.dto

import com.google.gson.annotations.SerializedName
import com.atm.olympuscourierapp.domain.model.Usuario

data class UsuarioDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("id_ruc") val id_ruc: String?,
    @SerializedName("documento") val documento: String?,
    @SerializedName("nombres") val nombres: String?,
    @SerializedName("apellidos") val apellidos: String?,
    @SerializedName("telefono") val telefono: String?,
    @SerializedName("correo") val correo: String?,
    @SerializedName("fecha_nac") val fecha_nac: String?,
    @SerializedName("fecha_creacion") val fecha_creacion: String?,
    @SerializedName("clave") val clave: String?,
    @SerializedName("cod_rol") val cod_rol: String?,
    @SerializedName("activo") val activo: String?,
) {
    fun toDomain(): Usuario {
        return Usuario(
            id = id ?: throw IllegalArgumentException("ID no puede ser nulo"),
            documento = documento ?: "",
            nombres = nombres ?: "",
            apellidos = apellidos ?: "",
            telefono = telefono ?: "",
            correo = correo ?: "",
            rol = cod_rol ?: "",
            fecha_nacimiento = fecha_nac ?: "1999-01-01T00:00:00.000Z",
            fecha_creacion = fecha_creacion ?: "1999-01-01T00:00:00.000Z",
        )
    }
}

data class LoginRequest(
    val documento: String,
    val clave: String,
)