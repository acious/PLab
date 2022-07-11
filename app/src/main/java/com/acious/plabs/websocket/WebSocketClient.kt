package com.acious.plabs.websocket

import android.util.Log
import com.acious.plabs.model.WebSocketResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import okhttp3.*
import okio.ByteString

class WebSocketClient(
    private val okHttpClient: OkHttpClient
) {
    private lateinit var scope: CoroutineScope
    private lateinit var incoming: Channel<WebSocketResponse>
    private lateinit var incomingFlow: Flow<WebSocketResponse>
    private var webSocket: WebSocket? = null

    fun connectWebSocket(scope: CoroutineScope, request: Request) {
        this.scope = scope
        incoming = Channel()
        incomingFlow = incoming.consumeAsFlow()
        webSocket = okHttpClient.newWebSocket(request, WebSocketChannelListener(incoming))
    }

    fun getIncoming(): Flow<WebSocketResponse> {
        return incomingFlow
    }

    fun isClosed(): Boolean {
        return incoming.isClosedForReceive
    }

    inner class WebSocketChannelListener(
        private val incoming: Channel<WebSocketResponse>,
    ) : WebSocketListener() {

        override fun onOpen(webSocket: WebSocket, response: Response) {
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            scope.launch(Dispatchers.IO) {
                if (OrderBookFilter.filter(text)) {
                    val response = Gson().fromJson(text, WebSocketResponse::class.java)
                    incoming.send(response)
                }
            }
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {

        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            Log.d("onFailure", t.message.toString())
            incoming.close(t)
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            Log.d("onClosing", "t.message.toString()")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            Log.d("onClosed", "onClosed")
            incoming.close()
        }
    }
}