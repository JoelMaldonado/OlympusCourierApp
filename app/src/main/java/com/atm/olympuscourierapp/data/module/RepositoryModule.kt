package com.atm.olympuscourierapp.data.module

import com.atm.olympuscourierapp.data.repository.CommonRepositoryImpl
import com.atm.olympuscourierapp.data.repository.RepartoRepositoryImpl
import com.atm.olympuscourierapp.data.repository.UsuarioRepositoryImpl
import com.atm.olympuscourierapp.domain.repository.CommonRepository
import com.atm.olympuscourierapp.domain.repository.RepartoRepository
import com.atm.olympuscourierapp.domain.repository.UsuarioRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun commonRepo(impl: CommonRepositoryImpl): CommonRepository

    @Binds
    abstract fun repartoRepo(impl: RepartoRepositoryImpl): RepartoRepository

    @Binds
    abstract fun usuarioRepo(impl: UsuarioRepositoryImpl): UsuarioRepository

}