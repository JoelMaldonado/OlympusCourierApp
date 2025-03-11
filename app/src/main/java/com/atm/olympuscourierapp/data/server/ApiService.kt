package com.atm.olympuscourierapp.data.server

import com.atm.olympuscourierapp.data.dto.request.DarConformidadRequest
import com.atm.olympuscourierapp.data.dto.request.SubirMercaderiaRequest
import com.jjmf.android.olympuscourierapp.data.server.dto.DistritoDto
import com.jjmf.android.olympuscourierapp.data.server.dto.LoginDto
import com.jjmf.android.olympuscourierapp.data.server.dto.LoginRequest
import com.jjmf.android.olympuscourierapp.data.server.dto.RepartoDto
import com.jjmf.android.olympuscourierapp.data.server.dto.UsuarioDto
import com.jjmf.android.olympuscourierapp.data.server.dto.VehiculoDto
import com.jjmf.android.olympuscourierapp.data.server.dto.Wrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("api/repartos/darConformidad")
    suspend fun darConformidad(@Body request: DarConformidadRequest) : Response<Wrapper<Nothing>>

    @GET("api/repartos/{id}")
    suspend fun getReparto(@Path("id") id:Int) : Response<Wrapper<RepartoDto>>

    @GET("api/usuarios/verificarToken")
    suspend fun verificarToken() : Response<Wrapper<UsuarioDto>>

    @POST("api/usuarios/login")
    suspend fun login(@Body request: LoginRequest): Response<Wrapper<LoginDto>>

    @GET("api/distrito")
    suspend fun listarDistritos(
        @Query("estado") estado:String
    ) : Response<Wrapper<List<DistritoDto>>>

    @GET("api/vehiculo")
    suspend fun listarVehiculos(
        @Query("estado") estado:String = "S"
    ) : Response<Wrapper<List<VehiculoDto>>>

    @POST("api/repartos/subirMercaderia")
    suspend fun subirMercaderia(@Body request: SubirMercaderiaRequest) : Response<Wrapper<Nothing>>

    @POST("api/repartos/cancelarReparto")
    suspend fun cancelarReparto(@Query ("id") id:Int) : Response<Wrapper<Nothing>>
}

