package com.jjmf.android.olympuscourierapp.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ItemReparto(
    val id:Int,
    val num_guia:String,
    val detalle:String,
    val precio:String,
    val adicional: Double,
    var clave:String,

    var claveInsert: MutableState<String> = mutableStateOf("")
)
