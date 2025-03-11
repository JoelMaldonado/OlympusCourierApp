package com.atm.olympuscourierapp.domain.enum

enum class EstadoEnvioEnum(val symbol: String, val nombre: String) {
    Pendiente("P", "Pendiente"),
    EnRuta("C", "En Ruta"),
    Entregado("E","Entregado"),
}