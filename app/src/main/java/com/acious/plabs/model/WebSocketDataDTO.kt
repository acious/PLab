package com.acious.plabs.model

data class WebSocketDataDTO(
    val symbol: String?,
    val id: Long,
    val side: String?,
    val price: Double,
    val size: Float,
    val timestamp: String?
)
