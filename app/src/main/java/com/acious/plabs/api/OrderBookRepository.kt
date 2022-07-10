package com.acious.plabs.api

import com.acious.plabs.model.WebSocketResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface OrderBookRepository {
    suspend fun connectOrderBook(scope: CoroutineScope) : Flow<WebSocketResponse>
}