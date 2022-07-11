package com.acious.plabs.model.orderbook

data class OrderBookVO(
    var id : Int,
    var buyQty: String?,
    var buyPrice: String?,
    var buyBgSize: Float,
    var sellQty: String?,
    var sellPrice: String?,
    var sellBgSize: Float,
)
