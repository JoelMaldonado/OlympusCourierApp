package com.jjmf.android.olympuscourierapp.data.server.dto

import com.atm.olympuscourierapp.domain.model.EstadoReparto
import com.jjmf.android.olympuscourierapp.domain.model.NewReparto
import com.atm.olympuscourierapp.util.toDate

data class NewRepartoDto(
    val id: Int?,
    val num_reparto: Int?,
    val id_usuario: Int?,
    val usuario: String?,
    val id_subido: Int?,
    val subido: String?,
    val entregado: String?,
    val cliente: String?,
    val telefono: String?,
    val direccion: String?,
    val distrito: String?,
    val fecha_creacion: String?,
    val estado: String?,
    val activo: String?,
    val costo_adicional: Double?,
    val costo_reparto: Double?,
    val comprobante: String?,
    val items: List<ItemRepartoDto>?
) {
    fun toDomain(): NewReparto {

        val estado = when (estado) {
            "P" -> EstadoReparto.Pendiente
            "C" -> EstadoReparto.EnCurso
            "E" -> EstadoReparto.Entregado
            "A" -> EstadoReparto.Anulado
            else -> throw IllegalArgumentException("Estado no reconocido")
        }

        return NewReparto(
            id = id ?: 0,
            num_reparto = num_reparto ?: 0,
            id_usuario = id_usuario ?: 0,
            usuario = usuario ?: "",
            id_subido = id_subido ?: 0,
            subido = subido ?: "",
            entregado = entregado ?: "",
            cliente = cliente ?: "",
            telefono = telefono ?: "",
            direccion = direccion ?: "",
            distrito = distrito ?: "",
            fecha_creacion = fecha_creacion?.toDate(),
            estado = estado,
            activo = activo ?: "",
            costo_adicional = costo_adicional ?: 0.0,
            costo_reparto = costo_reparto ?: 0.0,
            comprobante = comprobante ?: "",
            items = items?.map { it.toDomain() } ?: emptyList()
        )
    }
}