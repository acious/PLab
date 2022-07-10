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
    private val okHttpClient: OkHttpClient,
    private val request: Request
) {
    private lateinit var scope: CoroutineScope
    private val incoming = Channel<WebSocketResponse>()
    private val incomingFlow: Flow<WebSocketResponse> = incoming.consumeAsFlow()

    fun connectWebSocket(scope: CoroutineScope) {
        this.scope = scope
        okHttpClient.newWebSocket(request, WebSocketChannelListener(incoming))
        okHttpClient.dispatcher.executorService.shutdown()
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
            Log.d("onFailure", t.printStackTrace().toString())
            incoming.close(t)
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {

        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            incoming.close()
        }
    }
}