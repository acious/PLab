package com.acious.plabs.orderbook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acious.plabs.databinding.ItemOrderbookLayoutBinding
import com.acious.plabs.model.OrderBookVO

class OrderBookListAdapter : RecyclerView.Adapter<OrderBookListAdapter.OrderBookVH>() {
    private lateinit var items: List<OrderBookVO>

    inner class OrderBookVH(private val binding: ItemOrderbookLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(orderBookVO: OrderBookVO) {
            binding.orderBookVO = orderBookVO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderBookVH {
        val binding =
            ItemOrderbookLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderBookVH(binding)
    }

    override fun onBindViewHolder(holder: OrderBookVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

