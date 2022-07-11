package com.acious.plabs.model.recenttrade

import com.acious.plabs.model.RECENT_TRADE_VISIBLE_ITEM_COUNT
import com.acious.plabs.model.WebSocketDataDTO
import com.acious.plabs.model.WebSocketResponse
import com.acious.plabs.util.RecentTradeListWrapper
import com.acious.plabs.util.TimeUtil

class RecentTradeStorage {
    private var recentTradeListWrapper = RecentTradeListWrapper(RECENT_TRADE_VISIBLE_ITEM_COUNT)

    private val recentTradeVOMapper: (WebSocketDataDTO) -> RecentTradeVO = { dataDTO ->
        val price = dataDTO.price.toString()
        val qty = dataDTO.size.toString()
        var millisecond = 0L
        var time = ""
        dataDTO.timestamp?.let {
            millisecond = TimeUtil.timestampToMillisecond(it)
            time = TimeUtil.timeFormatWithMillisecond(millisecond)
        }

        RecentTradeVO(price, qty, time, millisecond)
    }

    fun handleData(response: WebSocketResponse) {
        response.data?.let { items ->
            val recentTradeVOs = items.map(recentTradeVOMapper)
            recentTradeListWrapper.addAll(recentTradeVOs)
        }
    }

    fun getVisibleList(): List<RecentTradeVO> {
        return recentTradeListWrapper.getList()
    }
}