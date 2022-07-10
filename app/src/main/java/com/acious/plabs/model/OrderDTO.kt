package com.acious.plabs.model

data class OrderDTO(
    val symbol: String?,
    val id: Long?,
    val side: String?,
    val size: Long?,
    val timestamp: String?
)
