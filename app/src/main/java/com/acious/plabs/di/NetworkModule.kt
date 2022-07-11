package com.acious.plabs.di

import com.acious.plabs.websocket.WebSocketClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit
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
    @Named("RecentTradeRequest")
    fun provideRecentTradeRequest(): Request {
        val baseUrl = "wss://ws.bitmex.com/realtime?subscribe=instrument,trade:XBTUSD"
        return Request.Builder()
            .url(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    @Named("OkHttp")
    fun provideNetworkClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .connectionPool(ConnectionPool(5, 5, TimeUnit.MINUTES))
            .build()
    }

    @Provides
    @Singleton
    @Named("WebSocketClient")
    fun provideWebSocketClient(@Named("OkHttp") okHttpClient: OkHttpClient): WebSocketClient {
        return WebSocketClient(okHttpClient)
    }
}