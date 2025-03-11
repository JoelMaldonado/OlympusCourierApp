package com.jjmf.android.olympuscourierapp.ui.features.VerCodigo

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerCodigoViewModel @Inject constructor(

) : ViewModel() {
    var metodoPagoTab by mutableStateOf(MetodoPagoTab.Yape)
}