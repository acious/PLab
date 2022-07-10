package com.acious.plabs.di

import com.acious.plabs.api.OrderBookRepository
import com.acious.plabs.api.OrderBookRepositoryImpl
import com.acious.plabs.websocket.WebSocketClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun provideOrderBookRepository(
        @Named("OkHttp") okHttpClient: OkHttpClient,
        @Named("OrderBookRequest") reqeust: Request
    ): OrderBookRepository {
        return OrderBookRepositoryImpl(WebSocketClient(okHttpClient, reqeust))
    }
}