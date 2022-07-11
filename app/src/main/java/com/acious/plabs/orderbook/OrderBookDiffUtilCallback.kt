package com.acious.plabs.orderbook

import androidx.recyclerview.widget.DiffUtil
import com.acious.plabs.model.orderbook.OrderBookVO

class OrderBookDiffUtilCallback : DiffUtil.ItemCallback<OrderBookVO>() {
    override fun areItemsTheSame(oldItem: OrderBookVO, newItem: OrderBookVO): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: OrderBookVO, newItem: OrderBookVO): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}