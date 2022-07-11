package com.acious.plabs.model

data class WebSocketResponse(
    val table : String?, // orderBookL2 or instrument
    val action : String?,
    val data : List<WebSocketDataDTO>?

)