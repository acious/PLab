package com.acious.plabs.recenttrade

import androidx.recyclerview.widget.DiffUtil
import com.acious.plabs.model.recenttrade.RecentTradeVO

class RecentTradeDiffUtilCallback : DiffUtil.ItemCallback<RecentTradeVO>() {
    override fun areItemsTheSame(oldItem: RecentTradeVO, newItem: RecentTradeVO): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: RecentTradeVO, newItem: RecentTradeVO): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}