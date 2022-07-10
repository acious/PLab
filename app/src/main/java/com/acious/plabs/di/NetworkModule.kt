package com.acious.plabs.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("OrderBookRequest")
    fun provideOrderBookRequest(): Request {
        val baseUrl = "wss://ws.bitmex.com/realtime?subscribe=instrument,orderBookL2:XBTUSD"
        return Request.Builder()
            .url(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    @Named("OkHttp")
    fun provideNetworkClient(): OkHttpClient {
        return OkHttpClient()
    }
}