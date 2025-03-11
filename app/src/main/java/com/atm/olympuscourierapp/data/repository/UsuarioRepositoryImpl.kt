package com.atm.olympuscourierapp.data.repository

import com.atm.olympuscourierapp.core.EstadosResult
import com.atm.olympuscourierapp.domain.repository.UsuarioRepository
import com.atm.olympuscourierapp.data.server.ApiService
import com.atm.olympuscourierapp.data.server.ApiService2
import com.atm.olympuscourierapp.domain.enum.TipoUsuario
import com.jjmf.android.olympuscourierapp.data.server.dto.LoginDto
import com.jjmf.android.olympuscourierapp.data.server.dto.LoginRequest
import com.atm.olympuscourierapp.domain.model.Usuario
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val api2: ApiService2
) : UsuarioRepository {
    override suspend fun login(doc: String, clave: String): EstadosResult<LoginDto> {
        return try {
            val request = LoginRequest(
                documento = doc,
                clave = clave
            )
            val call = api.login(request)
            if (call.isSuccessful) {
                if (call.body()?.isSuccess == true) EstadosResult.Correcto(call.body()?.data)
                else EstadosResult.Error(call.body()?.mensaje.toString())
            } else EstadosResult.Error(call.message())
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun getSesion(): EstadosResult<Usuario> {
        return try {
            val call = api.verificarToken()
            if (call.isSuccessful) {
                if (call.body()?.isSuccess == true) EstadosResult.Correcto(call.body()?.data?.toDomain())
                else EstadosResult.Error(call.body()?.mensaje.toString())
            } else EstadosResult.Error(call.message())
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun getAll(
        tipoUsuario: TipoUsuario?
    ): EstadosResult<List<Usuario>> {
        return try {
            val call = api2.getAllUsuarios(
                tipoUsuario = tipoUsuario?.code
            )
            if (call.isSuccessful && call.body() != null) {
                EstadosResult.Correcto(call.body()?.map { it.toDomain() })
            } else {
                EstadosResult.Error(call.message())
            }
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

}