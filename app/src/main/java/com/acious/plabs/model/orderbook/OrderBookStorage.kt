package com.acious.plabs.model.orderbook

import com.acious.plabs.model.Action
import com.acious.plabs.model.ORDERBOOK_VISIBLE_ITEM_COUNT
import com.acious.plabs.model.WebSocketDataDTO
import com.acious.plabs.model.WebSocketResponse
import java.util.*
import kotlin.collections.ArrayList

class OrderBookStorage {
    private lateinit var visibleList: ArrayList<OrderBookVO>
    private val buyTreeMap = TreeMap<Double, WebSocketDataDTO>(Collections.reverseOrder())
    private val sellTreeMap = TreeMap<Double, WebSocketDataDTO>()
    private var screenHalfSize: Int = 0

    fun handleData(response: WebSocketResponse) {
        if (response.action == Action.DELETE.name) {
            response.data?.let { items ->
                for (item in items) {
                    if (item.side == "Buy") {
                        buyTreeMap.remove(item.price)
                    } else if (item.side == "Sell") {
                        sellTreeMap.remove(item.price)
                    }
                }
            }
        } else {
            response.data?.let { items ->
                for (item in items) {
                    if (item.side == "Buy") {
                        buyTreeMap[item.price] = item
                    } else if (item.side == "Sell") {
                        sellTreeMap[item.price] = item
                    }
                }
            }
        }
    }

    fun makeVisibleList(): List<OrderBookVO> {
        val sumOfBuySizeList = ArrayList<Float>()
        val sumOfSellSizeList = ArrayList<Float>()

        visibleList = ArrayList(ORDERBOOK_VISIBLE_ITEM_COUNT)
        for (i in 1..ORDERBOOK_VISIBLE_ITEM_COUNT) {
            visibleList.add(OrderBookVO(i, null, null, 0f, null, null, 0f))
        }

        for ((i, item) in buyTreeMap.values.withIndex()) {
            if (i == ORDERBOOK_VISIBLE_ITEM_COUNT) {
                break
            }
            if (i == 0) {
                sumOfBuySizeList.add(item.size)
            } else {
                sumOfBuySizeList.add(sumOfBuySizeList[i - 1] + item.size)
            }
            visibleList[i].buyPrice = item.price.toString()
            visibleList[i].buyQty = item.size.toString()
        }

        for ((i, item) in sellTreeMap.values.withIndex()) {
            if (i == ORDERBOOK_VISIBLE_ITEM_COUNT) {
                break
            }
            if (i == 0) {
                sumOfSellSizeList.add(item.size)
            } else {
                sumOfSellSizeList.add(sumOfSellSizeList[i - 1] + item.size)
            }
            visibleList[i].sellPrice = item.price.toString()
            visibleList[i].sellQty = item.size.toString()
        }

        var sumOfBuySize = 0f
        if (sumOfBuySizeList.isNotEmpty()) {
            sumOfBuySize = sumOfBuySizeList.last()
        }

        var sumOfSellSize = 0f
        if (sumOfSellSizeList.isNotEmpty()) {
            sumOfSellSize = sumOfSellSizeList.last()
        }

        val pivotSum = sumOfBuySize + sumOfSellSize

        for ((i, item) in sumOfBuySizeList.withIndex()) {
            visibleList[i].buyBgSize = screenHalfSize * (item / pivotSum)
        }

        for ((i, item) in sumOfSellSizeList.withIndex()) {
            visibleList[i].sellBgSize = screenHalfSize * (item / pivotSum)
        }

        return visibleList
    }

    fun setScreenHalfSize(screenHalfSize: Int) {
        this.screenHalfSize = screenHalfSize
    }
}