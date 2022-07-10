package com.acious.plabs.websocket

import org.json.JSONObject

object OrderBookFilter {

    @JvmStatic
    fun filter(response : String) : Boolean {
        val jsonObject = JSONObject(response)
        if (jsonObject.has("table")) {
            if (jsonObject.getString("table").contains("orderBook")) {
                return true
            }
        }

        return false
    }
}
