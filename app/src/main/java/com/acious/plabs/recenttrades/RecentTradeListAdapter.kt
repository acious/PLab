package com.acious.plabs.recenttrades

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acious.plabs.databinding.ItemRecentTradeLayoutBinding
import com.acious.plabs.model.RecentTradeVO

class RecentTradeListAdapter : RecyclerView.Adapter<RecentTradeListAdapter.RecentTradeVH>() {
    private lateinit var items: List<RecentTradeVO>

    inner class RecentTradeVH(private val binding: ItemRecentTradeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recentTradeVO: RecentTradeVO) {
            binding.recentTradeVO = recentTradeVO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentTradeVH {
        val binding =
            ItemRecentTradeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentTradeVH(binding)
    }

    override fun onBindViewHolder(holder: RecentTradeVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

