package com.acious.plabs.orderbook

import androidx.recyclerview.widget.DiffUtil
import com.acious.plabs.model.OrderBookVO

class OrderBookDiffUtilCallback : DiffUtil.ItemCallback<OrderBookVO>() {
    override fun areItemsTheSame(oldItem: OrderBookVO, newItem: OrderBookVO): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: OrderBookVO, newItem: OrderBookVO): Boolean {
        return (oldItem.buyPrice == newItem.buyPrice)
                && (oldItem.buyQty == newItem.buyQty)
                && (oldItem.sellPrice == newItem.sellPrice)
                && (oldItem.sellQty == newItem.sellQty)
    }
}