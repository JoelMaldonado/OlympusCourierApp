package com.atm.olympuscourierapp.data.repository

import com.atm.olympuscourierapp.core.EstadosResult
import com.atm.olympuscourierapp.domain.model.EstadoEnvio
import com.atm.olympuscourierapp.domain.repository.CommonRepository
import com.atm.olympuscourierapp.data.server.ApiService
import com.atm.olympuscourierapp.data.server.ApiService2
import com.jjmf.android.olympuscourierapp.domain.model.Distrito
import com.jjmf.android.olympuscourierapp.domain.model.Vehiculo
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val api2: ApiService2
) : CommonRepository {
    override suspend fun getAllEstadoEnvios(): EstadosResult<List<EstadoEnvio>> {
        return try {
            val response = api2.getAllTipoEstado()
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                EstadosResult.Correcto(body.map { it.toDomain() })
            } else {
                EstadosResult.Error(response.message())
            }
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun getAllDistritos(): EstadosResult<List<Distrito>> {
        return try {
            val response = api.listarDistritos(
                estado = "S"
            )
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.isSuccess == true) EstadosResult.Correcto(body.data?.map { it.toDomain() })
                else EstadosResult.Error(body?.mensaje ?: "Error al obtener los distritos")
            } else {
                EstadosResult.Error("Error al obtener los distritos")
            }
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun getAllVehiculos(): EstadosResult<List<Vehiculo>> {
        return try {
            val call = api.listarVehiculos()
            if (call.isSuccessful) {
                if (call.body()?.isSuccess == true) EstadosResult.Correcto(call.body()?.data?.map { it.toDomain() })
                else EstadosResult.Error(call.body()?.mensaje ?: "Error al listar")
            } else {
                EstadosResult.Error(call.message())
            }
        } catch (error: Exception) {
            EstadosResult.Error(error.message.toString())
        }
    }
}