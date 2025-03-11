package com.atm.olympuscourierapp.domain.model

import androidx.compose.ui.graphics.Color
import com.atm.olympuscourierapp.ui.theme.ColorP2
import com.atm.olympuscourierapp.ui.theme.ColorR1
import com.atm.olympuscourierapp.ui.theme.ColorS1
import com.atm.olympuscourierapp.ui.theme.ColorT1
import com.jjmf.android.olympuscourierapp.domain.model.Cliente
import com.jjmf.android.olympuscourierapp.domain.model.ItemReparto
import java.text.SimpleDateFormat
import java.util.Locale

enum class EstadoReparto(val color: Color, val nombre: String) {
    Pendiente(color = ColorT1, nombre = "Pendiente"),
    EnCurso(color = ColorS1, nombre = "En Ruta"),
    Entregado(color = ColorP2, nombre = "Entregado"),
    Anulado(color = ColorR1, nombre = "Anulado")
}

data class Reparto(
    val id: Int,
    val num_reparto: Int,
    val anotacion: String,
    val estado: EstadoReparto,
    val fecha_creacion: String,
    val fecha_entrega: String,
    val id_cliente: Int,
    val cliente: Cliente,
    val id_usuario: Int,
    val usuario: Usuario,
    val id_repartidor: Int,
    val items: List<ItemReparto>,
    val total: Double
){

    fun formatFecha(pattern: String = "dd/MM/yyyy"): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val date = inputFormat.parse(fecha_creacion)
        return if (date!=null){
            val outputFormat = SimpleDateFormat(pattern, Locale.getDefault())
            return outputFormat.format(date)
        }else "Sin Formato"
    }

    fun total(): Double {
        return items.sumOf { it.precio?.toDoubleOrNull() ?: 0.0 }
    }

    fun totalAdicional() : Double {
        return items.sumOf { it.adicional }
    }

    fun formatoID() = num_reparto.toString().take(6).padStart(6, '0')
}