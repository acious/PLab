package com.acious.plabs.recenttrade

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acious.plabs.databinding.ItemRecentTradeLayoutBinding
import com.acious.plabs.model.recenttrade.RecentTradeVO

class RecentTradeListAdapter :
    ListAdapter<RecentTradeVO, RecentTradeListAdapter.RecentTradeVH>(RecentTradeDiffUtilCallback()) {
    inner class RecentTradeVH(private val binding: ItemRecentTradeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recentTradeVO: RecentTradeVO) {
            binding.recentTradeVO = recentTradeVO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentTradeVH {
        return RecentTradeVH(
            ItemRecentTradeLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecentTradeVH, position: Int) {
        holder.bind(getItem(position))
    }
}

