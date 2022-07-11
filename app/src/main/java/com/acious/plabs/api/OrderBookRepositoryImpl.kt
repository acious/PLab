package com.acious.plabs.api

import com.acious.plabs.model.WebSocketResponse
import com.acious.plabs.websocket.WebSocketClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import okhttp3.Request

class OrderBookRepositoryImpl(
    private val client: WebSocketClient,
    private val request: Request
) : OrderBookRepository {
    override suspend fun connectOrderBook(scope: CoroutineScope): Flow<WebSocketResponse> {
        client.connectWebSocket(scope, request)
        return client.getIncoming()
    }
}