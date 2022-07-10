package com.acious.plabs.model

import java.util.*
import kotlin.collections.ArrayList

class OrderBookStorage {
    private lateinit var visibleList: ArrayList<OrderBookVO>
    private val buyTreeMap = TreeMap<Double, OrderDTO>(Collections.reverseOrder())
    private val sellTreeMap = TreeMap<Double, OrderDTO>()

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

    fun getVisibleList(): List<OrderBookVO> {
        visibleList = ArrayList(ORDERBOOK_VISIBLE_ITEM_COUNT)
        for (i in 1..ORDERBOOK_VISIBLE_ITEM_COUNT) {
            visibleList.add(OrderBookVO(null, null, null, null))
        }

        var idx = 0
        for (item in buyTreeMap.values) {
            if (idx == ORDERBOOK_VISIBLE_ITEM_COUNT) {
                break
            }
            visibleList[idx].buyPrice = item.price.toString()
            visibleList[idx].buyQty = item.size.toString()
            idx++
        }

        idx = 0
        for (item in sellTreeMap.values) {
            if (idx == ORDERBOOK_VISIBLE_ITEM_COUNT) {
                break
            }
            visibleList[idx].sellPrice = item.price.toString()
            visibleList[idx].sellQty = item.size.toString()
            idx++
        }

        return visibleList
    }
}