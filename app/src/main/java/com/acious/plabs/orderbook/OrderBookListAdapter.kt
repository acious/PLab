package com.acious.plabs.orderbook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acious.plabs.databinding.ItemOrderbookLayoutBinding
import com.acious.plabs.model.orderbook.OrderBookVO

class OrderBookListAdapter :
    ListAdapter<OrderBookVO, OrderBookListAdapter.OrderBookVH>(OrderBookDiffUtilCallback()) {
    inner class OrderBookVH(private val binding: ItemOrderbookLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OrderBookVO) {
            binding.orderBookVO = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderBookVH {
        return OrderBookVH(
            ItemOrderbookLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderBookVH, position: Int) {
        holder.bind(getItem(position))
    }
}

