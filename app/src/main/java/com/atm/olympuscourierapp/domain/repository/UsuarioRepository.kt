package com.atm.olympuscourierapp.domain.repository

import com.atm.olympuscourierapp.core.EstadosResult
import com.atm.olympuscourierapp.domain.enum.TipoUsuario
import com.jjmf.android.olympuscourierapp.data.server.dto.LoginDto
import com.atm.olympuscourierapp.domain.model.Usuario

interface UsuarioRepository {
    suspend fun login(doc:String, clave:String) : EstadosResult<LoginDto>
    suspend fun getSesion() : EstadosResult<Usuario>
    suspend fun getAll(tipoUsuario: TipoUsuario?): EstadosResult<List<Usuario>>
}