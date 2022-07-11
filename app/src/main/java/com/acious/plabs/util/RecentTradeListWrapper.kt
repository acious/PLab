package com.acious.plabs.util

import com.acious.plabs.model.recenttrade.RecentTradeVO
import kotlin.collections.ArrayList

class RecentTradeListWrapper(private val size: Int) {
    private var delegate: ArrayList<RecentTradeVO> = ArrayList()
    private var oldestTime = Long.MIN_VALUE

    fun getList(): List<RecentTradeVO> {
        return delegate
    }

    fun addAll(list: List<RecentTradeVO>): Boolean {
        if (delegate.isEmpty()) {
            initList(list)
            return true
        }

        for (item in list) {
            if (item.milliseconds > oldestTime) {
                addItem(item)
            }
        }
        return true
    }

    private fun initList(elements: List<RecentTradeVO>) {
        delegate = updateList(elements)
    }

    private fun addItem(item: RecentTradeVO) {
        delegate.add(item)
        delegate = updateList(delegate)

    }

    private fun updateList(prevList: List<RecentTradeVO>): java.util.ArrayList<RecentTradeVO> {
        val newSortedList = ArrayList<RecentTradeVO>()

        prevList.sortedByDescending {
            it.milliseconds
        }

        for (i in 1..size) {
            newSortedList.add(prevList[i - 1])
        }

        oldestTime = newSortedList.last().milliseconds
        return newSortedList
    }
}