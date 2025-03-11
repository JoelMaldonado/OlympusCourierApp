package com.atm.olympuscourierapp.data.repository

import com.atm.olympuscourierapp.core.EstadosResult
import com.atm.olympuscourierapp.data.dto.request.DarConformidadRequest
import com.atm.olympuscourierapp.data.dto.request.SubirMercaderiaRequest
import com.atm.olympuscourierapp.domain.enum.EstadoEnvioEnum
import com.atm.olympuscourierapp.domain.model.Reparto
import com.atm.olympuscourierapp.domain.repository.RepartoRepository
import com.atm.olympuscourierapp.data.server.ApiService
import com.atm.olympuscourierapp.data.server.ApiService2
import com.jjmf.android.olympuscourierapp.domain.model.NewReparto
import javax.inject.Inject

class RepartoRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val api2: ApiService2
) : RepartoRepository {

    override suspend fun listarRepartos(
        fecha: String?,
        estado: String?,
        id_distrito: Int?,
        id_vehiculo: Int?,
        id_subido: Int?
    ): EstadosResult<List<NewReparto>> {
        return try {
            val call = api2.listarRepartos(
                page = 1,
                limit = 100,
                activo = "S",
                desde = fecha,
                hasta = fecha,
                estado_envio = estado,
                id_distrito = id_distrito,
                id_vehiculo = id_vehiculo,
                id_subido = id_subido
            )
            return if (call.isSuccessful) {
                EstadosResult.Correcto(call.body()?.data?.map { it.toDomain() })
            } else EstadosResult.Error(call.message())
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun get(idReparto: Int): EstadosResult<Reparto> {
        return try {
            val call = api.getReparto(idReparto)
            if (call.isSuccessful) {
                if (call.body()?.isSuccess == true) EstadosResult.Correcto(call.body()?.data?.toDomain())
                else EstadosResult.Error("No se encontro")
            } else EstadosResult.Error(call.message())
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun subirMercaderia(idReparto: Int): EstadosResult<String> {
        return try {
            val body = SubirMercaderiaRequest(idReparto)
            val call = api.subirMercaderia(body)
            if (call.isSuccessful) {
                if (call.body()?.isSuccess == true) EstadosResult.Correcto(call.body()?.mensaje)
                else EstadosResult.Error(call.body()?.mensaje.toString())
            } else EstadosResult.Error(call.message())
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun darConformidad(idReparto: Int, urlFoto: String): EstadosResult<String> {
        return try {
            val request = DarConformidadRequest(
                id_reparto = idReparto,
                url_foto = urlFoto
            )
            val call = api.darConformidad(request)
            if (call.isSuccessful) {
                if (call.body()?.isSuccess == true) EstadosResult.Correcto(call.body()?.mensaje)
                else EstadosResult.Error(call.body()?.mensaje.toString())
            } else EstadosResult.Error(call.message())
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun cancelarReparto(idReparto: Int): EstadosResult<Nothing> {
        return try {
            val call = api.cancelarReparto(idReparto)
            if (call.isSuccessful) {
                if (call.body()?.isSuccess == true) {
                    EstadosResult.Correcto(null)
                } else {
                    EstadosResult.Error(call.body()?.mensaje.toString())
                }
            } else {
                EstadosResult.Error(call.message())
            }
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

}