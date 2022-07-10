package com.acious.plabs.orderbook

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acious.plabs.api.OrderBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderBookViewModel @Inject constructor(
    var repository: OrderBookRepository
) : ViewModel() {

    fun initView() {
        viewModelScope.launch {
            repository.connectOrderBook(this).collect {
                it.data?.let { data ->
                    Log.d("OrderViewModel data", data.toString())
                }
            }
        }
    }
}