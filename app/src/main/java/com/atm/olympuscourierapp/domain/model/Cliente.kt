package com.jjmf.android.olympuscourierapp.domain.model

data class Cliente(
    val tipo_doc:String,
    val documento:String,
    val nombres:String,
    val telefono:String,
    val correo:String,
    val genero:String,
    val id_distrito:Int,
    val distrito:String,
    val direc:String,
    val referencia:String,
    val urlMaps:String
){

    fun formatTelefono(): String {
        return telefono.chunked(3).joinToString(" ")
    }
}
