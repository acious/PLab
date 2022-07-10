package com.acious.plabs.api

import com.acious.plabs.model.WebSocketResponse
import com.acious.plabs.websocket.WebSocketClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class OrderBookRepositoryImpl(private val client : WebSocketClient) : OrderBookRepository {

    override suspend fun connectOrderBook(scope: CoroutineScope): Flow<WebSocketResponse> {
        client.connectWebSocket(scope)
        return client.getIncoming()
    }
}