package com.atm.olympuscourierapp.data.server

import com.atm.olympuscourierapp.data.dto.EstadoEnvioDto
import com.atm.olympuscourierapp.data.server.res.GetRepartosResponse
import com.atm.olympuscourierapp.domain.model.Usuario
import com.jjmf.android.olympuscourierapp.data.server.dto.UsuarioDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService2 {

    @GET("api/reparto")
    suspend fun listarRepartos(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("activo") activo: String,
        @Query("desde") desde: String?,
        @Query("hasta") hasta: String?,
        @Query("estado") estado_envio: String?,
        @Query("id_distrito") id_distrito: Int?,
        @Query("id_vehiculo") id_vehiculo: Int?,
        @Query("id_subido") id_subido: Int?
    ): Response<GetRepartosResponse>

    @GET("api/tipo-estado")
    suspend fun getAllTipoEstado(): Response<List<EstadoEnvioDto>>

    @GET("api/usuario")
    suspend fun getAllUsuarios(
        @Query("tipoUsuario") tipoUsuario: String?
    ): Response<List<UsuarioDto>>
}