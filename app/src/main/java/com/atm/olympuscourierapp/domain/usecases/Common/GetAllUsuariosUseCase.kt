package com.atm.olympuscourierapp.domain.usecases.Common

import com.atm.olympuscourierapp.core.EstadosResult
import com.atm.olympuscourierapp.domain.enum.TipoUsuario
import com.atm.olympuscourierapp.domain.model.Usuario
import com.atm.olympuscourierapp.domain.repository.UsuarioRepository
import javax.inject.Inject

class GetAllUsuariosUseCase @Inject constructor(
    private val repo: UsuarioRepository
) {
    suspend operator fun invoke(tipoUsuario: TipoUsuario?): List<Usuario> {
        return when (val res = repo.getAll(tipoUsuario)) {
            is EstadosResult.Correcto -> res.datos.orEmpty()
            is EstadosResult.Error -> throw Exception(res.mensajeError)
        }
    }
}