package com.jjmf.android.olympuscourierapp.domain.model

import com.atm.olympuscourierapp.domain.model.EstadoReparto
import java.util.Date

data class NewReparto(

    val id: Int,
    val num_reparto: Int,
    val id_usuario: Int,
    val usuario: String,
    val id_subido: Int,
    val subido: String,
    val entregado: String,
    val cliente: String,
    val telefono: String,
    val direccion: String,
    val distrito: String,
    val fecha_creacion: Date?,
    val estado: EstadoReparto,
    val activo: String,
    val costo_adicional: Double,
    val costo_reparto: Double,
    val comprobante: String,
    val items: List<ItemReparto>
) {
    fun formatoID() = num_reparto.toString().take(6).padStart(6, '0')
}