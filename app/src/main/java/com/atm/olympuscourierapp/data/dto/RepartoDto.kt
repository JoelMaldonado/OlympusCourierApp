package com.jjmf.android.olympuscourierapp.data.server.dto

import com.atm.olympuscourierapp.data.dto.ClienteDto
import com.google.gson.annotations.SerializedName
import com.jjmf.android.olympuscourierapp.domain.model.Cliente
import com.atm.olympuscourierapp.domain.model.EstadoReparto
import com.atm.olympuscourierapp.domain.model.Reparto
import com.atm.olympuscourierapp.domain.model.Usuario

data class RepartoDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("id_ruc") val id_ruc: Int?,
    @SerializedName("num_reparto") val num_reparto: Int?,
    @SerializedName("anotacion") val anotacion: String?,
    @SerializedName("estado") val estado: String?,
    @SerializedName("fecha_creacion") val fecha_creacion: String?,
    @SerializedName("fecha_entrega") val fecha_entrega: String?,
    @SerializedName("id_cliente") val id_cliente: Int?,
    @SerializedName("cliente") val cliente: ClienteDto?,
    @SerializedName("id_usuario") val id_usuario: Int?,
    @SerializedName("usuario") val usuario: UsuarioDto?,
    @SerializedName("id_repartidor") val id_repartidor: Int?,
    @SerializedName("items") val items: List<ItemRepartoDto>?,
    @SerializedName("total") val total: Double?,
) {
    fun toDomain(): Reparto {
        val estado = when (estado) {
            "P" -> EstadoReparto.Pendiente
            "C" -> EstadoReparto.EnCurso
            "E" -> EstadoReparto.Entregado
            "A" -> EstadoReparto.Anulado
            else -> throw IllegalArgumentException("Estado no reconocido")
        }
        return Reparto(
            id = id ?: 0,
            num_reparto = num_reparto ?: 0,
            anotacion = anotacion ?: "Sin Data",
            estado = estado,
            fecha_creacion = fecha_creacion ?: "Sin Data",
            fecha_entrega = fecha_entrega ?: "Sin Data",
            id_cliente = id_cliente ?: 0,
            cliente = cliente?.toDomain() ?: Cliente(
                tipo_doc = "Sin Data",
                documento = "Sin Data",
                nombres = "Sin Data",
                telefono = "Sin Data",
                correo = "Sin Data",
                genero = "Sin Data",
                id_distrito = 0,
                distrito = "Sin Data",
                direc = "Sin Data",
                referencia = "Sin Data",
                urlMaps = ""
            ),
            id_usuario = id_usuario ?: 0,
            usuario = usuario?.toDomain() ?: Usuario(
                documento = "Sin Valor",
                nombres = "Sin Valor",
                apellidos = "Sin Valor",
                telefono = "Sin Valor",
                correo = "Sin Valor",
                rol = "Sin Valor",
                fecha_nacimiento = "Sin Valor",
                fecha_creacion = "Sin Valor",
                id = 0
            ),
            id_repartidor = id_repartidor ?: 0,
            items = items?.map { it.toDomain() } ?: emptyList(),
            total = total ?: 0.0
        )
    }
}
