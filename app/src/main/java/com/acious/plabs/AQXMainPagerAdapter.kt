package com.acious.plabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.acious.plabs.orderbook.OrderBookFragment
import com.acious.plabs.recenttrade.RecentTradesFragment

class AQXMainPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return OrderBookFragment.newInstance()
            }
            1 -> {
                return RecentTradesFragment.newInstance()
            }
        }
        throw Exception()
    }
}