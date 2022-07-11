package com.acious.plabs.mvvm

import androidx.databinding.BindingAdapter
import com.acious.plabs.OrderBookItemBg


@BindingAdapter(value = ["bgWidth"], requireAll = true)
fun setBgWidth(bgView: OrderBookItemBg, size: Float) {
    bgView.setWidth(size)
}