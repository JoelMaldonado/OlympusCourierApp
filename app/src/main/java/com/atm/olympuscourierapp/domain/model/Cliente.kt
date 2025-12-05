package com.jjmf.android.olympuscourierapp.domain.model

data class Cliente(
    val tipoDoc:String,
    val documento:String,
    val nombres:String,
    val telefono:String,
    val correo:String,
    val genero:String,
    val idDistrito:Int,
    val distrito:String,
    val direc:String,
    val referencia:String,
    val urlMaps:String
){

    fun formatTelefono(): String {
        return telefono.chunked(3).joinToString(" ")
    }
}
