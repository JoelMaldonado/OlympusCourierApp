package com.atm.olympuscourierapp.ui.features.Perfil

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atm.olympuscourierapp.core.EstadosResult
import com.atm.olympuscourierapp.domain.repository.UsuarioRepository
import com.atm.olympuscourierapp.domain.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel() {

    var usuario by mutableStateOf<Usuario?>(null)
    var error by mutableStateOf<String?>(null)
    var loader by mutableStateOf(false)

    fun init() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                loader = true
                when(val res = repository.getSesion()){
                    is EstadosResult.Correcto -> usuario = res.datos
                    is EstadosResult.Error -> error = res.mensajeError
                }
            }catch (e:Exception){
                error = e.message
            }finally {
                loader = false
            }
        }
    }

}