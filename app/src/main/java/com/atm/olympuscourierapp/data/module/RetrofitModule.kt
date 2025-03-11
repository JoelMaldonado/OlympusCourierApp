package com.atm.olympuscourierapp.data.module

import com.atm.olympuscourierapp.app.BaseApp.Companion.prefs
import com.atm.olympuscourierapp.data.server.ApiService
import com.atm.olympuscourierapp.data.server.ApiService2
import com.atm.olympuscourierapp.util.Constantes.BASE_URL
import com.atm.olympuscourierapp.util.Constantes.NEW_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val token = prefs.getToken()
                val request = chain.request().newBuilder()
                    .header("Authorization", "$token")
                    .build()
                chain.proceed(request)
            }
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepartoService(okHttpClient: OkHttpClient): ApiService2 {
        return Retrofit.Builder()
            .baseUrl(NEW_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(ApiService2::class.java)
    }

}