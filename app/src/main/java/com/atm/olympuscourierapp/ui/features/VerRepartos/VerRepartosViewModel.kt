package com.atm.olympuscourierapp.ui.features.VerRepartos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atm.olympuscourierapp.core.EstadosResult
import com.atm.olympuscourierapp.domain.enum.TipoUsuario
import com.atm.olympuscourierapp.domain.repository.RepartoRepository
import com.atm.olympuscourierapp.domain.usecases.Common.GetAllDistritoUseCase
import com.atm.olympuscourierapp.domain.usecases.Common.GetAllEstadoEnvioUseCase
import com.atm.olympuscourierapp.domain.usecases.Common.GetAllUsuariosUseCase
import com.atm.olympuscourierapp.domain.usecases.Common.GetAllVehiculoUseCase
import com.atm.olympuscourierapp.util.format
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerRepartosViewModel @Inject constructor(
    private val repository: RepartoRepository,
    private val getAllVehiculoUseCase: GetAllVehiculoUseCase,
    private val getAllDistritoUseCase: GetAllDistritoUseCase,
    private val getAllEstadoEnvioUseCase: GetAllEstadoEnvioUseCase,
    private val getAllUsuariosUseCase: GetAllUsuariosUseCase
) : ViewModel() {

    var state by mutableStateOf(VerRepartosState())
        private set

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                state = state.copy(
                    listEstadoEnvio = getAllEstadoEnvioUseCase(),
                    listDistritos = getAllDistritoUseCase(),
                    listVehiculos = getAllVehiculoUseCase(),
                    listDeliverys = getAllUsuariosUseCase(TipoUsuario.DISTRIBUIDOR)
                )
            } catch (e: Exception) {
                state = state.copy(error = e.message)
            }
        }
    }

    private fun listarRepartos() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                state = state.copy(isLoading = true)
                delay(500)
                val res = repository.listarRepartos(
                    fecha = state.fecha.format("yyyy-MM-dd"),
                    estado = state.estadoFiltro?.codigo,
                    id_distrito = state.distritoFiltro?.id,
                    id_vehiculo = state.vehiculoFiltro?.id,
                    id_subido = state.deliveryFiltro?.id
                )

                when (res) {
                    is EstadosResult.Correcto -> {
                        state = state.copy(listRepartos = res.datos.orEmpty())
                    }

                    is EstadosResult.Error -> throw Exception(res.mensajeError)
                }
            } catch (e: Exception) {
                state = state.copy(error = e.message)
            } finally {
                state = state.copy(isLoading = false)
            }
        }
    }

    fun subirMercaderia(idReparto: Int) {
        viewModelScope.launch {
            try {
                when (val res = repository.subirMercaderia(idReparto)) {
                    is EstadosResult.Correcto -> {
                        setEvent(VerRepartoEvents.ListarRepartos)
                        throw Exception("Mercaderia subida correctamente")
                    }

                    is EstadosResult.Error -> throw Exception(res.mensajeError)
                }
            } catch (e: Exception) {
                state = state.copy(error = e.message)
            }
        }
    }

    fun setEvent(e: VerRepartoEvents) {
        when (e) {
            is VerRepartoEvents.SetDistrito -> state = state.copy(distritoFiltro = e.value)
            is VerRepartoEvents.SetEstadoEnvio -> state = state.copy(estadoFiltro = e.value)
            is VerRepartoEvents.SetVehiculo -> state = state.copy(vehiculoFiltro = e.value)
            is VerRepartoEvents.SetUsuario -> state = state.copy(deliveryFiltro = e.value)
            is VerRepartoEvents.SetFecha -> state = state.copy(fecha = e.value)
            VerRepartoEvents.ListarRepartos -> {
                listarRepartos()
            }

        }
    }


}