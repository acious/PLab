package com.acious.plabs.api

import com.acious.plabs.model.WebSocketResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface RecentTradeRepository {
    suspend fun connectRecentTrade(scope: CoroutineScope) : Flow<WebSocketResponse>
}