package com.acious.plabs.di

import com.acious.plabs.api.OrderBookRepository
import com.acious.plabs.api.OrderBookRepositoryImpl
import com.acious.plabs.api.RecentTradeRepository
import com.acious.plabs.api.RecentTradeRepositoryImpl
import com.acious.plabs.websocket.WebSocketClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Request
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun provideOrderBookRepository(
        @Named("WebSocketClient") webSocketClient: WebSocketClient,
        @Named("OrderBookRequest") request: Request
    ): OrderBookRepository {
        return OrderBookRepositoryImpl(webSocketClient, request)
    }

    @Provides
    fun provideRecentTradeRepository(
        @Named("WebSocketClient") webSocketClient: WebSocketClient,
        @Named("RecentTradeRequest") request: Request
    ): RecentTradeRepository {
        return RecentTradeRepositoryImpl(webSocketClient, request)
    }
}